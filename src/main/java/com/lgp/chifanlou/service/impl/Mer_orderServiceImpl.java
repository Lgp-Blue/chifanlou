package com.lgp.chifanlou.service.impl;

import com.lgp.chifanlou.mapper.Mer_orderMapper;
import com.lgp.chifanlou.pojo.Mer_order;
import com.lgp.chifanlou.service.Mer_orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Mer_orderServiceImpl implements Mer_orderService {
    @Autowired
    Mer_orderMapper mer_orderMapper;

    @Override
    public List<Mer_order> getMer_order(String mname) {
        List<Mer_order>  mos= mer_orderMapper.getMer_order(mname);
        for(int i=0;i<mos.size();i++){
            if(mos.get(i).getMname()!=null)
            {
                List<String> dishes=mer_orderMapper.getDishWithOrder(mos.get(i).getOrderid());
                mos.get(i).setDishes(dishes);
            }
        }
        return mos;
    }
}
