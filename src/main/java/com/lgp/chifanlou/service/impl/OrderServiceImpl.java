package com.lgp.chifanlou.service.impl;

import com.lgp.chifanlou.mapper.Mer_orderMapper;
import com.lgp.chifanlou.mapper.MerchantMapper;
import com.lgp.chifanlou.mapper.OrderMapper;
import com.lgp.chifanlou.pojo.Order;
import com.lgp.chifanlou.pojo.OrderDish;
import com.lgp.chifanlou.pojo.temp;
import com.lgp.chifanlou.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    MerchantMapper merchantMapper;
    @Autowired
    Mer_orderMapper mer_orderMapper;
    @Override
    public int add(Order order) {
         orderMapper.add(order);
         return order.getOrderid();
    }

    @Override
    public Order getOrderById(Integer orderid) {
        return orderMapper.getOrderById(orderid);
    }

    @Override
    public int update(Integer orderid) {
        return orderMapper.update(orderid);
    }

    @Override
    public int remove(Integer orderid) {
        return orderMapper.remove(orderid);
    }

    @Override
    public List<Order> getOrderByUserid(Integer userid) {
        return orderMapper.getOrderByUserid(userid);
    }

    @Override
    public List<Order> getPayingOrderByUserid(Integer userid) {
        return orderMapper.getPayingOrderByUserid(userid);
    }

    @Override
    public List<Order> getPaidOrderByUserid(Integer userid) {
        return orderMapper.getPaidOrderByUserid(userid);
    }

    @Override
    public void adddish(OrderDish orderDish) {
        String orderid=orderDish.getOrderid();
        List<String> dishes=orderDish.getDishes();
        System.out.println("sssssssssssssssssssssssssssssssssssssss");
        System.out.println(orderid);
        System.out.println(dishes);
        List<Integer> userdishes=new ArrayList<Integer>();
        for(int i=0;i<dishes.size();i++){
            temp t=new temp();
             t.setOrderid(orderid);
             t.setDishid(dishes.get(i));
             orderMapper.adddish(t);
        }
        mer_orderMapper.addMer_order(merchantMapper.getMidByName(orderDish.getMname()),orderid);

    }
}
