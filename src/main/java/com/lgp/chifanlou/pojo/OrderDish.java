package com.lgp.chifanlou.pojo;

import lombok.Data;

import java.util.List;
@Data
public class OrderDish {
    String mname;
    String userorderid;
    String orderid;
    List<String> dishes;
}
