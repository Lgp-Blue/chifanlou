package com.lgp.chifanlou.service.impl;

import com.lgp.chifanlou.mapper.BookMapper;
import com.lgp.chifanlou.pojo.Book;
import com.lgp.chifanlou.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Override
    public int add(Book book) {
        bookMapper.add(book);
        return book.getBookid();
    }

    @Override
    public int remove(Integer bookid) {
        return bookMapper.remove(bookid);
    }
}
