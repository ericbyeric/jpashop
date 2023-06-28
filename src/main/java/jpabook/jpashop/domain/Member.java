package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name= "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded   // 내장 타입
    private Address address;

    @OneToMany(mappedBy = "member")     // 일대다: order 테이블의 member 필드에 의해서 mapping이 된다
    private List<Order> orders = new ArrayList<>();



}
