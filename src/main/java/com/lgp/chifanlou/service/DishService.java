package com.lgp.chifanlou.service;


import com.lgp.chifanlou.pojo.Dish;

import java.util.List;

public interface DishService {
    List<Dish> showdish(String merchant);

    Integer insertdish(Dish dish);
}
