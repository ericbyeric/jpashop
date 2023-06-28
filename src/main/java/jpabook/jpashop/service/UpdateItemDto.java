package jpabook.jpashop.service;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateItemDto {
    public Long id;
    public String name;
    public int price;
    public int stockQuantity;
}
