package com.lgp.chifanlou.service.impl;

import com.lgp.chifanlou.mapper.MerchantMapper;
import com.lgp.chifanlou.pojo.Book;
import com.lgp.chifanlou.pojo.Merchant;
import com.lgp.chifanlou.pojo.Note;
import com.lgp.chifanlou.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    MerchantMapper merchantMapper;
    @Override
    public int insert(Merchant merchant) {
        return merchantMapper.insert(merchant);
    }
    @Override
    public Integer login(Merchant merchant) {
        return merchantMapper.login(merchant);
    }

    @Override
    public Integer check(Merchant merchant) {
        return merchantMapper.check(merchant);
    }

    @Override
    public Integer update(Merchant merchant) {
        return merchantMapper.update(merchant);
    }

    @Override
    public String getEmail(Merchant merchant) {
        return merchantMapper.getEmail(merchant);
    }

    @Override
    public String getMidByName(String name) {
        return merchantMapper.getMidByName(name);
    }

    @Override
    public List<Book> getBookBYMname(String mname) {
        return merchantMapper.getBookBYMname(mname);
    }

    @Override
    public List<Book> getDoBookBYMname(String mname) {
        return merchantMapper.getDoBookBYMname(mname);
    }

    @Override
    public Integer updateBook(String bookid) {
        return merchantMapper.updateBook(bookid);
    }

}
