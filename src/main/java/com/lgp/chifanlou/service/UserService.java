package com.lgp.chifanlou.service;

import com.lgp.chifanlou.pojo.Book;
import com.lgp.chifanlou.pojo.Coupon;
import com.lgp.chifanlou.pojo.User;

import java.util.List;

public interface UserService {
    int add(User user);
    int checkAdd(String openid);
    int getUseridByOpenid(String openid);
    List<Coupon> getCoupon(User user);
    List<Book> getBook(User user);
    User getUserById(Integer userid);
}
