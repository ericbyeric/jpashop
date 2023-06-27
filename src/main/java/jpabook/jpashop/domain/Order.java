package jpabook.jpashop.domain;

import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name="orders")
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
        delivery.getOrder(this);
    }
}