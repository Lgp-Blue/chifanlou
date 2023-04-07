package com.lgp.chifanlou.service.impl;

import com.lgp.chifanlou.mapper.DishMapper;
import com.lgp.chifanlou.pojo.Dish;
import com.lgp.chifanlou.service.DishService;
import com.lgp.chifanlou.utils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    DishMapper dishMapper;

    @Override
    public List<Dish> showdish(String merchant) {
        return dishMapper.showdish(merchant);
    }

    @Override
    public Integer insertdish(Dish dish) {
        return dishMapper.insertdish(dish);
    }
}
