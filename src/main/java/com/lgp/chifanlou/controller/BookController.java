package com.lgp.chifanlou.controller;


import com.lgp.chifanlou.base.Result;
import com.lgp.chifanlou.pojo.Book;
import com.lgp.chifanlou.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
     @PostMapping("/add")
     //添加预约并返回其主键
     public Result<Integer> addBook(@RequestBody Book book){
         Integer affect=bookService.add(book);
         if(affect!=null){
             Result<Integer> result=Result.ok();
             result.setData(affect);
             return result;
         }else {
             return  Result.fail();
         }

     }
    @GetMapping("/remove")
    public Result<Integer> removeBook(Integer bookid) {
        Integer affect=bookService.remove(bookid);
        if(affect!=null){
            Result<Integer> result=Result.ok();
            result.setData(affect);
            return result;
        }else {
            return  Result.fail();
        }
    }
}
