package com.lgp.chifanlou.pojo;

import lombok.Data;

@Data
public class Dish {
    Integer dishid;
    String dishname;
    String dishflavor;
    String dishplay;//菜品图片展示
    Integer dishnumber=0;
    String dishdiscount;//菜品打折力度（如:5.8）
    String dishprice;
    String merchant;
}
