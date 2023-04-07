package com.lgp.chifanlou.service;

import com.lgp.chifanlou.pojo.Order;
import com.lgp.chifanlou.pojo.OrderDish;

import java.util.List;

public interface OrderService {
    int add(Order order);
    //通过id查询订单
    Order getOrderById(Integer orderid);
    //更改订单的支付状态
    int update(Integer orderid);
    //取消订单
    int remove( Integer orderid);
    //通过用户id查询所有订单
    List<Order> getOrderByUserid(Integer userid);
    //通过用户id查询待支付订单
    List<Order> getPayingOrderByUserid(Integer userid);
    //通过用户id查询已支付订单
    List<Order> getPaidOrderByUserid(Integer userid);
    void adddish(OrderDish orderDish);
}
