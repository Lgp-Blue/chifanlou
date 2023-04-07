package com.lgp.chifanlou.mapper;


import com.lgp.chifanlou.pojo.Mer_order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Mer_orderMapper {
      @Select("SELECT * FROM `mer_order` where mname=#{mname} ORDER BY `orderid`;")
      public List<Mer_order> getMer_order(String mname);

    @Select("SELECT `dish`.`dishname` FROM `dish`,`orderdish` WHERE `dish`.`dishid`=`orderdish`.`dishid` AND `orderdish`.`orderid`=#{orderid};")
    List<String> getDishWithOrder(String orderid);
    @Insert("INSERT INTO `goforfood`.`merchantorder` (`mid`, `orderid`) VALUES (#{mid}, #{orderid}); ")
     public  Integer addMer_order(@Param("mid")String mid,@Param("orderid")String orderid);
}
