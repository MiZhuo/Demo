package site.mizhuo.seatademobank1.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.mizhuo.seatademobank1.mapper.CustDepDetailMapper;
import site.mizhuo.seatademobank1.service.CustDepDetailService;

/**
 * @author: Mizhuo
 * @time: 2021/3/29 10:29 下午
 * @description:
 */
@Service
@Slf4j
public class CustDepDetailServiceImpl implements CustDepDetailService {

    @Autowired
    CustDepDetailMapper mapper;

    @GlobalTransactional
    @Transactional
    @Override
    public void transfer(Integer id, double amount) {
        mapper.updateCustDepBal(id,amount * -1);
    }
}
