package com.lgp.chifanlou.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MerBillMapper {
    @Select("SELECT dishname  FROM `mer_dish_count` WHERE `mname`=#{mname};")
   public List<String> getMerBillDishByMname(String mname);
    @Select("SELECT num       FROM `mer_dish_count` WHERE `mname`=#{mname};")
    public List<Integer> getMerBillNumByMname(String mname);
}
