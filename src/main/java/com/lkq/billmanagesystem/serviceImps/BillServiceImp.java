package com.lkq.billmanagesystem.serviceImps;

import com.lkq.billmanagesystem.dao.BillMapper;
import com.lkq.billmanagesystem.entities.Bill;
import com.lkq.billmanagesystem.entities.BillSupplier;
import com.lkq.billmanagesystem.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BillServiceImp implements BillService {

    @Autowired
    BillMapper  billMapper;

    @Override
    public List<BillSupplier> getBills(Bill bill) {

        return  billMapper.getBills(bill);
    }


    @Override
    public BillSupplier findBillByBid(Integer bid) {

        return (BillSupplier) billMapper.findBillByBid(bid);
    }

    @Override
    public Integer addBill(Bill bill) {

        return  billMapper.addBill(bill);
    }

    @Override
    public Integer updateBill(Bill bill) {
        return billMapper.updateBill(bill);
    }

    @Override
    public Integer deleteBillByBid(Integer bid) {
        return billMapper.deleteBillByBid(bid);
    }
}
