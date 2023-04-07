package com.lgp.chifanlou.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lgp.chifanlou.base.Result;
import com.lgp.chifanlou.pojo.Book;
import com.lgp.chifanlou.pojo.Coupon;
import com.lgp.chifanlou.pojo.User;
import com.lgp.chifanlou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;
    @Value("${wx.grant_type}")
    private String grant_type;
    @Value("${wx.url}")
    private String url;
    @Autowired
    UserService userService;
    @PostMapping("/add")
    //添加用户并返回其主键
    public Result<Integer> add(@RequestBody User u){
        System.out.println(u);
        String code=u.getCode();
        System.out.println(code);
        Map<String,Object> json=null;
        RestTemplate restTemplate=new RestTemplate();
        url=url+"?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type="+grant_type;
        String data= restTemplate.getForObject(url,String.class);
        System.out.println(data);
        ObjectMapper mapper=new ObjectMapper();
        try{
            json=mapper.readValue(data,Map.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        String openid=(String) json.get("openid");
        System.out.println("------------------");
        System.out.println(openid);
        User user=new User();
        user.setNickname(u.getNickname());
        user.setAvatar(u.getAvatar());
        user.setOpenid(openid);
        Integer affect;
        if(userService.checkAdd(openid)>0){
            affect=userService.getUseridByOpenid(openid);
        }else {
            affect=userService.add(user);
        }

        if(affect!=null){
            Result<Integer> result=Result.ok();
            result.setData(affect);
            return result;
        }else {
            return  Result.fail();
        }
    }

    //获取当前用户的拥有的优惠券
    @GetMapping("/coupon")
    public Result myCoupon(String userid){
        Integer userid_=new Integer(userid);
        User user = userService.getUserById(userid_);
        if (user != null) {
            List<Coupon> coupons = userService.getCoupon(user);
            return Result.ok().setData(coupons);
        } else{
            return Result.fail();
        }
    }
    //获取当前用户的预约
    @GetMapping("/book")
    public Result myBook(@RequestParam int userid) {
        User user = userService.getUserById(userid);
        if (user != null) {
            List<Book> books = userService.getBook(user);
            return Result.ok().setData(books);
        } else{
            return Result.fail();
        }
    }



}
