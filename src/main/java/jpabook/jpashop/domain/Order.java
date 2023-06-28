package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name="orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")  // FK
    private Member member;  // 연관관계의 주인

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;     // 주문상태 [ORDER, CANCEL]


    //==연관관계 메서드==//
        // 핵심적으로 control하는 부분이 들고 있는 것이 좋다
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    // 아래와 같이 두개를 원자적으로 묶는 메서드이다
//        public static void main(String[] args) {
//            Member member = new Member();
//            Order order = new Order();
//
//            member.getOrders().add(order);
//            order.setMember(member);
//        }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }


    //==생성 메서드==//

    /**
     * 주문 생성
     */
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){  // 생성 메서드에서 주문 생성에 대한 비지니스 로직은 완결 시킨다.
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비지니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel(){
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료가 된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        orderItems.forEach(orderItem -> orderItem.cancel());    // 재고 수량을 원복 하기 위해서
    }


    //==조회 로직==//  계산이 필요할 때가 있다

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
//        int totalPrice = orderItems.stream().mapToInt(OrderItem::getOrderPrice).sum();
//        for(OrderItem orderItem : orderItems){
//            totalPrice += orderItem.getOrderPrice();
//        }
        // 이건 람다로 표현해본건데 지역변수를 사용할 수 없어서 못씀
//        orderItems.forEach(orderItem -> totalPrice += orderItem.getOrderPrice());
//        return totalPrice;

        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }



}