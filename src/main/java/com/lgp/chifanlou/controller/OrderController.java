package com.lgp.chifanlou.controller;


import com.lgp.chifanlou.base.Result;
import com.lgp.chifanlou.pojo.Order;
import com.lgp.chifanlou.pojo.OrderDish;
import com.lgp.chifanlou.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/add")
    public Result<Integer> addOrder(Order order){
        Integer affect=orderService.add(order);
        System.out.println(affect);
        if(affect!=null){
            Result<Integer> result=Result.ok();
            result.setData(affect);
            return result;
        }else {
            return  Result.fail();
        }
    }
    @GetMapping("/alldish")
    public Result<Integer> addAlldish(OrderDish orderDish){
            orderDish.setMname("1234");
            System.out.println(orderDish);
            orderService.adddish(orderDish);
            Result<Integer> result=Result.ok();
            result.setData(1);
            return result;
    }

    @GetMapping("/id")
    //通过id查询订单
    public Result<Order> getOrderById(Integer orderid){
             Order order=orderService.getOrderById(orderid);
        if(order!=null){
            Result<Order> result=Result.ok();
            result.setData(order);
            return result;
        }else {
            return  Result.fail();
        }
    };

    @GetMapping("/update")
    //更改订单的支付状态
    public  Result<Integer> update(Integer orderid){
        Integer affect=orderService.update(orderid);
        if(affect!=null){
            Result<Integer> result=Result.ok();
            result.setData(affect);
            return result;
        }else {
            return  Result.fail();
        }
    }

    @GetMapping("/remove")
     //取消订单
    public  Result<Integer>  remove( Integer orderid){
        Integer affect=orderService.remove(orderid);
        if(affect!=null){
            Result<Integer> result=Result.ok();
            result.setData(affect);
            return result;
        }else {
            return  Result.fail();
        }
    }

    @GetMapping("/all")
    //通过用户id查询所有订单
     public Result  getOrderByUserid(Integer userid){
        List<Order> orders = orderService.getOrderByUserid(userid);
        orders.forEach((o)->System.out.println(o));
        if (!orders.isEmpty()) {

            return Result.ok().setData(orders);
        } else{
            return Result.fail();
        }
    }
    //通过用户id查询待支付订单
    @GetMapping("/paying")
    public Result getPayingOrderByUserid(Integer userid){
        List<Order> orders = orderService.getPayingOrderByUserid(userid);
        if (!orders.isEmpty()) {
            return Result.ok().setData(orders);
        } else{
            return Result.fail();
        }
    }


    //通过用户id查询已支付订单
    @GetMapping("/paid")
   public Result getPaidOrderByUserid(Integer userid){
    List<Order> orders = orderService.getPaidOrderByUserid(userid);
    if (!orders.isEmpty()) {
        return Result.ok().setData(orders);
    } else{
        return Result.fail();
    }

     }

}
