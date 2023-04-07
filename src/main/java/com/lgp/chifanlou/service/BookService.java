package com.lgp.chifanlou.service;


import com.lgp.chifanlou.pojo.Book;

public interface BookService {
    int add(Book book);
    int remove(Integer bookid);
}
