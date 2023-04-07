package com.lgp.chifanlou.service.impl;

import com.lgp.chifanlou.mapper.MerBillMapper;
import com.lgp.chifanlou.pojo.MerBill;
import com.lgp.chifanlou.service.MerBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MerBillServiceImpl implements MerBillService {
    @Autowired
    MerBillMapper merBillMapper;
    @Override
    public Map<List<String>,List<Integer>> getMerBillByMname(String mname) {
        List<String> mbs= merBillMapper.getMerBillDishByMname(mname);
        List<Integer> mns= merBillMapper.getMerBillNumByMname(mname);
        Map<List<String>,List<Integer>> bmap=new HashMap<List<String>,List<Integer>>();
        bmap.put(mbs,mns);
        return bmap;
    }
}
