package com.lgp.chifanlou.service;

import com.lgp.chifanlou.pojo.MerBill;

import java.util.List;
import java.util.Map;

public interface MerBillService {
    public Map<List<String>,List<Integer>> getMerBillByMname(String mname);
}
