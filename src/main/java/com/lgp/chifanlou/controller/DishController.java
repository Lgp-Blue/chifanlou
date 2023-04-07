package com.lgp.chifanlou.controller;

import com.lgp.chifanlou.base.Result;
import com.lgp.chifanlou.pojo.Dish;
import com.lgp.chifanlou.service.DishService;
import com.lgp.chifanlou.utils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class DishController {
    @Autowired
    DishService dishService;
    @Autowired
    AliyunOSSUtil aliyunOSSUtil;

    @RequestMapping("index")
    public String intercept(HttpServletResponse response, HttpSession httpSession)
    {
        response.setCharacterEncoding("gb2312");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(httpSession.getAttribute("loginuser")==null){
            out.print("<script>alert('请完成登录！');window.location.href='tologin';</script>");
            out.flush();
            out.close();
        }

        return "index";
    }



    @GetMapping("showdish")
    public String showDish(HttpSession httpSession,HttpServletRequest request,HttpServletResponse response) {
        response.setCharacterEncoding("gb2312");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(httpSession.getAttribute("loginuser")==null){
            out.print("<script>alert('请完成登录！');window.location.href='tologin';</script>");
            out.flush();
            out.close();
        }
        String merchant=httpSession.getAttribute("loginuser").toString();
        List<Dish> dishes =dishService.showdish(merchant);
        dishes.forEach((o)->System.out.println(o));
        request.setAttribute("dishes",dishes);
        return "menu";
    }


    @GetMapping("insertdish")
    public String insertdish(HttpSession httpSession,HttpServletResponse response) {
        response.setCharacterEncoding("gb2312");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(httpSession.getAttribute("loginuser")==null){
            out.print("<script>alert('请完成登录！');window.location.href='tologin';</script>");
            out.flush();
            out.close();
        }
        return "insertdish";
    }
    @PostMapping(value = "doinsertdish")
    public  String doinsertdish(@RequestParam("dishname") String dishname,
                                @RequestParam("dishflavor") String dishflavor,
                                @RequestParam("dishprice") String dishprice,
                                @RequestParam("dishdiscount") String dishdiscount,
                                @RequestParam("dishplay") MultipartFile dishplay,
        HttpServletResponse response,HttpSession httpSession,HttpServletRequest request) throws UnsupportedEncodingException {
        response.setCharacterEncoding("gb2312");
        //前端编码的中文字段，在此解码
        dishname= URLDecoder.decode(dishname, StandardCharsets.UTF_8.name());
        dishflavor= URLDecoder.decode(dishflavor, StandardCharsets.UTF_8.name());


        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(httpSession.getAttribute("loginuser")==null){
            out.print("<script>alert('请完成登录！');window.location.href='tologin';</script>");
            out.flush();
            out.close();
        }
        if(dishname==""||dishflavor==""||dishdiscount==""||dishprice==""||dishplay.isEmpty())
        {
            out.print("<script>alert('请完整填写信息');history.go(-1);</script>");
            out.flush();
            out.close();
        }

        String directoryPath = "D:\\images";
        File directory = new File(directoryPath); //查找是否存在指定目录，不存在就创建
        if (!directory.exists()) {
            boolean created = directory.mkdirs();

            if (created) {
                System.out.println("Directory was created successfully!");
            } else {
                System.out.println("Failed to create directory!");

            }
        } else {
            System.out.println("Directory already exists!");
        }

        String dishImageUrl="";
        try {
            byte[] bytes = dishplay.getBytes();
            Path path = Paths.get("D:\\images/" + dishplay.getOriginalFilename());
            Files.write(path, bytes);
            File file=new File("D:\\images/" + dishplay.getOriginalFilename());
            dishImageUrl= aliyunOSSUtil.upload((file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String merchant=httpSession.getAttribute("loginuser").toString();
        Dish dish=new Dish();dish.setDishname(dishname);dish.setDishplay(dishImageUrl);dish.setDishdiscount(dishdiscount);dish.setDishflavor(dishflavor);dish.setDishprice(dishprice);dish.setMerchant(merchant);
        System.out.println(dish);
        if(dishService.insertdish(dish)<=0){
            out.print("<script>alert('Failed to add dish');history.go(-1);</script>");
            out.flush();
            out.close();
        }
        out.print("<script>alert('The dishes are added');history.go(-1);</script>");
        out.flush();
        out.close();
        return "index";
    }

}