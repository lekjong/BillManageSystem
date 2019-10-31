package com.lkq.billmanagesystem.services;

import com.lkq.billmanagesystem.entities.Supplier;

import java.util.List;


public interface SupplierService {

    List<com.lkq.billmanagesystem.entities.Supplier> getSuppliers(Supplier supplier);

    Supplier findSupplierBySid(Integer sid);

    Integer    addSupplier(Supplier supplier);

    Integer    deleteSupplierById(Integer sid);

    Integer    updateSupplier(Supplier supplier);

}
