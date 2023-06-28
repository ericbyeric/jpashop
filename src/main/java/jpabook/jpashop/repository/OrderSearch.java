package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.List;

@Getter @Setter
public class OrderSearch {

    // parameter 조건들
    private String memberName;      // 회원 이름
    private OrderStatus orderStatus;    // 주문 상태[ORDER, CANCEL]

}
