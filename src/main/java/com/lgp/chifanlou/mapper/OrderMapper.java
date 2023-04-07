package com.lgp.chifanlou.mapper;

import com.lgp.chifanlou.pojo.Order;
import com.lgp.chifanlou.pojo.temp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    //提交待支付的订单
    @Insert("INSERT INTO `goforfood`.`order` (`orderprice`, `orderstate`,`userid`) " +
            "VALUES (#{orderprice}, #{orderstate},#{userid})")
    @Options(useGeneratedKeys = true,keyProperty = "orderid",keyColumn = "orderid")
    int add(Order order);


    @Insert("INSERT INTO `goforfood`.`orderdish` (`orderid`, `dishid`) VALUES (#{orderid}, #{dishid});")
    @Options(useGeneratedKeys = true,keyProperty = "userorderid",keyColumn = "userorderid")
    int adddish(temp temp);

    //通过id查询订单
    @Select("SELECT * FROM `order` WHERE `order`.`orderid`=#{orderid}")
    Order getOrderById(@Param("orderid")Integer orderid);


    //更改订单的支付状态
    @Update("UPDATE `goforfood`.`order` SET `orderstate` = '已付款' WHERE `orderid` =#{orderid}")
    int update(@Param("orderid") Integer orderid);


    //取消订单
    @Delete("DELETE FROM `goforfood`.`order` WHERE `orderid` = #{orderid}")
    int remove(@Param("orderid") Integer orderid);

    //通过用户id查询所有订单
    @Select("SELECT * FROM `order` WHERE `order`.`userid`=#{userid}")
    List<Order> getOrderByUserid(@Param("userid")Integer userid);


    //通过用户id查询待支付订单
    @Select("SELECT * FROM `order` WHERE `order`.`userid`=#{userid} AND `orderstate`=\"待支付\"")
    List<Order> getPayingOrderByUserid(@Param("userid")Integer userid);
    //通过用户id查询已支付订单
    @Select("SELECT * FROM `order` WHERE `order`.`userid`=#{userid} AND `orderstate`=\"已支付\"")
    List<Order> getPaidOrderByUserid(@Param("userid")Integer userid);





}
