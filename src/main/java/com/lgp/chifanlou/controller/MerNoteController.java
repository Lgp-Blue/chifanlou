package com.lgp.chifanlou.controller;

import com.lgp.chifanlou.pojo.Note;
import com.lgp.chifanlou.service.MerNoteService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MerNoteController {
    @Autowired
    MerNoteService merNoteService;
    @RequestMapping("insertNote")
    public void sendNote(Note note, HttpSession httpSession, HttpServletResponse response) throws AddressException, MessagingException, IOException {
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
        String loginuser=(String) httpSession.getAttribute("loginuser");
        System.out.println(loginuser);
        System.out.println("+++++"+note);
        note.setAuthor(loginuser);
        System.out.println("+++++"+note);
        this.merNoteService.insertNote(note);
    }
    @RequestMapping("getNotes")
    @ResponseBody
    public List<Map<Object,Object>> getNotesByAuthor(HttpSession httpSession){
        List<Map<Object,Object>> MTmap=new ArrayList<Map<Object,Object>>();
     if (httpSession.getAttribute("loginuser")==null)
            return MTmap;
        else{
          MTmap=this.merNoteService.getNotesByAuthor(httpSession.getAttribute("loginuser").toString());
//         MTmap=this.merNoteService.getNotesByAuthor("1234");
            return MTmap;
      }
    };
    @PostMapping("deleteNote")
    @ResponseBody
    public void deleteNoteByID(Integer noteID){
        this.merNoteService.deleteNote(noteID);
    };
    @PostMapping("updateNote")
    @ResponseBody
    public void deleteNoteByID(Note note){
        this.merNoteService.updateNote(note);
    };

}
