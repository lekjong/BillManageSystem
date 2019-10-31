package com.lkq.billmanagesystem.services;

import com.lkq.billmanagesystem.entities.Bill;
import com.lkq.billmanagesystem.entities.BillSupplier;

import java.util.List;

public interface BillService {

    List<BillSupplier> getBills(Bill bill);

    BillSupplier   findBillByBid(Integer bid);

    Integer  addBill(Bill bill);

    Integer  updateBill(Bill bill);

    Integer  deleteBillByBid(Integer bid);

}
