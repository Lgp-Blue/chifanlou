package com.lgp.chifanlou.service;

import com.lgp.chifanlou.pojo.Book;
import com.lgp.chifanlou.pojo.Merchant;
import com.lgp.chifanlou.pojo.Note;

import java.util.List;

public interface MerchantService {

    public int insert(Merchant merchant);
    public Integer login(Merchant merchant);
    public Integer check(Merchant merchant);
    public Integer update(Merchant merchant);
    public String getEmail(Merchant merchant);
    public String getMidByName(String name);
    public List<Book> getBookBYMname(String mname);
    public List<Book> getDoBookBYMname(String mname);
    public Integer updateBook(String bookid);
}
