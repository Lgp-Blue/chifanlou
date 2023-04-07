package com.lgp.chifanlou.controller;


import com.lgp.chifanlou.base.Result;
import com.lgp.chifanlou.service.MerBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@RestController
public class MerBillController {
    @Autowired
    MerBillService merBillService;

    @PostMapping("toDishBill")
    public Map<List<String>,List<Integer>>  toregister(HttpSession httpSession) {
        Map<List<String>,List<Integer>> bmap=new  HashMap<List<String>, List<Integer>>();

        if (httpSession.getAttribute("loginuser")==null)
            return bmap;
        else{
            bmap=merBillService.getMerBillByMname(httpSession.getAttribute("loginuser").toString());
            return bmap;
        }
    }

}
