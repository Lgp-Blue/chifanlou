package com.lgp.chifanlou.controller;

import com.lgp.chifanlou.pojo.Mer_order;
import com.lgp.chifanlou.service.Mer_orderService;
import com.lgp.chifanlou.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@Controller
public class Mer_orderController {
    @Autowired
    Mer_orderService mer_orderService;
    @Autowired
    MerchantService merchantService;


    @GetMapping("showMer_Order")
    public String showMer_Order(HttpSession httpSession, HttpServletResponse response, HttpServletRequest request)
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
            List<Mer_order> mo= mer_orderService.getMer_order(httpSession.getAttribute("loginuser").toString());
            mo.forEach((o)-> System.out.println(o));
            request.setAttribute("molist",mo);
            return "order";

    }

}
