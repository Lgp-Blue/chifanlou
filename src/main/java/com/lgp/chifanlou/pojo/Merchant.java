package com.lgp.chifanlou.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Merchant {
    private String mid;
    private String mname;
    private String password;
    private String email;
    private String phone;
    private String gender;
    private String code;
    private boolean isExist;
    List<User> musers;
    List<Mer_order> mer_orders;
}