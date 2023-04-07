package com.lgp.chifanlou.service.impl;
import com.lgp.chifanlou.mapper.UserMapper;
import com.lgp.chifanlou.pojo.Book;
import com.lgp.chifanlou.pojo.Coupon;
import com.lgp.chifanlou.pojo.User;
import com.lgp.chifanlou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public int add(User user) {
        userMapper.add(user);
        Integer userid=user.getUserid();
        userMapper.allot_coupon(userid);
        return user.getUserid();
    }

    @Override
    public int checkAdd(String openid) {
        return userMapper.checkAdd(openid);
    }

    @Override
    public int getUseridByOpenid(String openid) {
        return userMapper.getUseridByOpenid(openid);
    }

    @Override
    public List<Coupon> getCoupon(User user) {
        return userMapper.getCoupon(user);
    }

    @Override
    public List<Book> getBook(User user) {
        return userMapper.getBook(user);
    }

    @Override
    public User getUserById(Integer userid) {
        return userMapper.getUserById(userid);
    }
}
