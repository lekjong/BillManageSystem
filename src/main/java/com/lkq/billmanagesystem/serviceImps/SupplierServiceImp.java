package com.lkq.billmanagesystem.serviceImps;

import com.lkq.billmanagesystem.dao.SupplierMapper;
import com.lkq.billmanagesystem.entities.Supplier;
import com.lkq.billmanagesystem.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SupplierServiceImp implements SupplierService {

    @Autowired
    SupplierMapper  supplierMapper;

    @Override
    public List<Supplier> getSuppliers(Supplier supplier) {

        return  supplierMapper.getSuppliers(supplier);

    }

    @Override
    public Supplier findSupplierBySid(Integer sid) {

        return (Supplier) supplierMapper.findSupplierBySid(sid);

    }


    @Override
    public Integer addSupplier(Supplier supplier) {

        return supplierMapper.addSupplier(supplier);

    }

    @Override
    public Integer deleteSupplierById(Integer pid) {

        return supplierMapper.deleteSupplierById(pid);

    }

    @Override
    public Integer updateSupplier(Supplier supplier) {

        return supplierMapper.updateSupplier(supplier);

    }
}
