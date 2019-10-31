package com.lkq.billmanagesystem.dao;

import com.lkq.billmanagesystem.entities.Bill;
import com.lkq.billmanagesystem.entities.BillSupplier;

import java.util.List;

public interface BillMapper {

  List<BillSupplier> getBills(Bill bill);

  Bill   findBillByBid(Integer bid);

  Integer  addBill(Bill bill);

  Integer  updateBill(Bill bill);

  Integer  deleteBillByBid(Integer bid);

}
