package com.lgp.chifanlou.mapper;
import com.lgp.chifanlou.pojo.Dish;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {
    @Select("select dishid,dishname,dishflavor,dishplay,dishdiscount,dishprice from dish where merchant=#{merchant}")
    List<Dish> showdish(String merchant);

    @Insert("INSERT INTO `goforfood`.`dish` (`dishname`, `dishflavor`, `dishplay`, `dishdiscount`, `dishprice`) VALUES (#{dishname}, #{dishflavor}, #{dishplay}, #{dishdiscount}, #{dishprice});")
    @Options(useGeneratedKeys = true,keyProperty = "dishid",keyColumn = "dishid")
    Integer insertdish(Dish dish);


}

