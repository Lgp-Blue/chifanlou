package com.lgp.chifanlou.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Mer_order {
    String mname;
    String orderid;
    String orderprice;
    String orderstate;
    String ordertime;
    List<String> dishes;
}
