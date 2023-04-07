package com.lgp.chifanlou.pojo;

import lombok.Data;

import java.util.List;
@Data
public class Order {
    Integer orderid;
    Double orderprice;
    String orderstate;
    String ordertime;
    Integer userid;
    List<Dish> dishes;
}
