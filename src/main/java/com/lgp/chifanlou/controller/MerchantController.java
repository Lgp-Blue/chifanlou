package com.lgp.chifanlou.controller;

import com.lgp.chifanlou.pojo.Book;
import com.lgp.chifanlou.pojo.Merchant;
import com.lgp.chifanlou.pojo.Note;
import com.lgp.chifanlou.service.MerchantService;
import com.lgp.chifanlou.utils.Mail;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class MerchantController {

    @Autowired
    MerchantService merchantService;
    @GetMapping("toregister")
    public String toregister() {
        return "register";
    }
    @GetMapping("tologin")
    public String tologin() {
        return "login";
    }
    @GetMapping("toEmailCheck")
    public String toEmailCheck() {
        return "emailCheck";
    }
    @GetMapping("toForget")
    public String toForget() {
        return "forget";
    }
    @GetMapping("toManager")
    public String toManager() {
        return "manager";
    }
    @GetMapping("showDishBill")
    public String getDishBill(HttpSession httpSession, HttpServletResponse response){
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
        return  "merbill";
    }


    @RequestMapping("")//避免没登录直接进主页
    public String to_login(HttpSession httpSession,HttpServletResponse response) {
        response.setCharacterEncoding("gb2312");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(httpSession.getAttribute("loginuser")==null){
            out.print("<script>window.location.href='tologin';</script>");
            out.flush();
            out.close();
        }
        return "index";
    }

    @GetMapping("toExit")
    public void toExit(HttpSession httpSession,HttpServletResponse response) {
        response.setCharacterEncoding("gb2312");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!(httpSession.getAttribute("loginuser")==null)){
            httpSession.removeAttribute("loginuser");
            out.print("<script>alert('您已退出！');window.location.href='tologin';</script>");
            out.flush();
            out.close();
        }else {
            out.print("<script>alert('您尚未登录！');window.location.href='tologin';</script>");
            out.flush();
            out.close();
        }

    }



    @GetMapping("toChangePassword")
    public String toChangePassword(HttpSession httpSession,HttpServletResponse response) {
        response.setCharacterEncoding("gb2312");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(httpSession.getAttribute("flag")==null){
            out.print("<script>alert('请完成验证！');window.location.href='toForget';</script>");
            out.flush();
            out.close();
        }

        return "changePassword";
    }

    @RequestMapping("insert")
    public  void insert(Merchant merchant, HttpSession httpSession, HttpServletResponse response){
        response.setCharacterEncoding("gb2312");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(this.merchantService.check(merchant)>0){
            out.print("<script>alert('用户名重复');history.go(-1);</script>");
            out.flush();
            out.close();
        }
        System.out.println(merchant);
        if(merchant.getEmail()==""||merchant.getMname()==""||merchant.getPassword()==""){
            out.print("<script>alert('请填写必填项');history.go(-1);</script>");
            out.flush();
            out.close();
        }
        if(!httpSession.getAttribute("code").equals(merchant.getCode()))
        {
            System.out.println(httpSession.getAttribute("code")+"ss");
            System.out.println(merchant.getCode());
            out.print("<script>alert('验证码错误');history.go(-1);</script>");
            out.flush();
            out.close();
        }
        this.merchantService.insert(merchant);
        out.print("<script>alert('注册成功'); window.location.href='tologin'</script>");
        out.flush();
        out.close();
    }

    @RequestMapping("checkname")
    @ResponseBody
    public  Merchant checkname(@RequestBody Merchant merchant) throws AddressException, MessagingException, IOException{


        System.out.println(merchant);
        boolean isExist=this.merchantService.check(merchant)>0;
        Merchant check=new Merchant();
        check.setExist(isExist);
        return check;
    }



    @RequestMapping("login")
    public  String login(Merchant merchant,HttpSession httpSession,HttpServletResponse response){
        response.setCharacterEncoding("gb2312");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(merchant.getMname()==""||merchant.getPassword()==""){
            out.print("<script>alert('请填写必填项');history.go(-1);</script>");
            out.flush();
            out.close();
        }
        System.out.println(merchant);
        int flag=this.merchantService.login(merchant);
        if(flag<=0)
        {
            out.print("<script>alert('请检查账号或者密码');history.go(-1);</script>");
            out.flush();
            out.close();
        }
        if(!merchant.getMname().equals("")){
            httpSession.setAttribute("loginuser",merchant.getMname());}

        return "index";
    }

    @RequestMapping("/check")
    public  String check(Merchant merchant,HttpSession httpSession,HttpServletResponse response) throws javax.mail.MessagingException, MessagingException{
        response.setCharacterEncoding("gb2312");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(merchant.getMname()==""){
            out.print("<script>alert('请输入用户名');history.go(-1);</script>");
            out.flush();
            out.close();
        }
        String email=this.merchantService.getEmail(merchant);
        String item=(int)(Math.random()*9000+1000)+"";
        httpSession.setAttribute("code",item);
        Mail.sendMail(email,"您的验证码为："+item);
        return "wait";
    }

    @PostMapping("doEmailCheck")
    public void doEmailCheck(String code,HttpSession httpSession,HttpServletResponse response)
    {
        response.setCharacterEncoding("gb2312");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!code.equals(httpSession.getAttribute("code").toString())){
            out.print("<script>alert('验证码错误');window.location.href='toForget';</script>");
            out.flush();
            out.close();
        }
        else{
            httpSession.setAttribute("flag","true");
            out.print("<script>alert('请重新设置密码');window.location.href='toChangePassword';</script>");
            out.flush();
            out.close();
        }
    }

    @RequestMapping("/doChangePassword")
    public  void update(Merchant merchant,HttpServletResponse response,HttpSession httpSession){
        httpSession.removeAttribute("flag");
        response.setCharacterEncoding("gb2312");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(merchant.getMname()==""||merchant.getPassword()==""){
            out.print("<script>alert('请填写必填项');history.go(-1);</script>");
            out.flush();
            out.close();
        }
        this.merchantService.update(merchant);
        out.print("<script>alert('密码修改成功');window.location.href='tologin';</script>");
        out.flush();
        out.close();
    }

    @GetMapping("showMbook")
    public String showMbook(HttpSession httpSession, HttpServletResponse response, HttpServletRequest request)
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

        List<Book> mb=merchantService.getBookBYMname(httpSession.getAttribute("loginuser").toString());
        request.setAttribute("mblist",mb);
        return "showbook";

    }
    @GetMapping("showDMbook")
    public String showDMbook(HttpSession httpSession, HttpServletResponse response, HttpServletRequest request)
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

        List<Book> mdb=merchantService.getDoBookBYMname(httpSession.getAttribute("loginuser").toString());
        request.setAttribute("mdblist",mdb);
        return "showdbook";

    }
    @RequestMapping("/toedit")
    public  void toedit(String bookid,HttpServletResponse response,HttpSession httpSession){
        System.out.println("ddddddddddddddddddddddddddd");
        System.out.println(bookid);
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
        this.merchantService.updateBook(bookid);
        out.print("<script>alert('预约状态修改成功');window.location.href='showDMbook';</script>");
        out.flush();
        out.close();
    }

}
