package com.lgp.chifanlou.pojo;

import lombok.Data;

import java.util.List;
@Data
public class User {
    Integer userid;
    String nickname;
    String avatar;
    String openid;
    String code;
    List<Order> uorders;
    List<Coupon> ucoupons;
    List<Book> ubooks;
}