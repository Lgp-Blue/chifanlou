package com.lgp.chifanlou.pojo;

import lombok.Data;

@Data
public class Book {

    Integer bookid;
    Integer userid;
    String booktype;
    String bookstate;
    String booktime;
    Integer turnout;
    String contactname;
    String contactsex;
    String contactphone;
}
