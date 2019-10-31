package com.lkq.billmanagesystem.entities;


/**
 * 该类为Bill的包装类，在其基础上添加了Supplier的相关属性；
 */
public class BillSupplier extends Bill {

      private String  supplierName;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
