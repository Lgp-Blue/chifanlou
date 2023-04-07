package com.lgp.chifanlou.controller;

import com.lgp.chifanlou.base.Result;
import com.lgp.chifanlou.pojo.Dish;
import com.lgp.chifanlou.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class UDishController {
    @Autowired
    DishService dishService;
    @GetMapping("")
    public Result showDish(String merchant) {

        List<Dish> dishes =dishService.showdish(merchant);
        dishes.forEach((o)->System.out.println(o));
        return Result.ok().setData(dishes);
    }
}