package com.lkq.billmanagesystem.dao;

import com.lkq.billmanagesystem.entities.Supplier;

import java.util.List;


public interface SupplierMapper {

    //查询所有供应商
//    @Select("SELECT * FROM t_supplier" )
    List<Supplier> getSuppliers(Supplier supplier);

    //根据供应商Id查询
//    @Select("SELECT * FROM t_supplier WHERE pid = #{pid}")
    Supplier findSupplierBySid(Integer sid);

    //添加供应商
//    @Options(useGeneratedKeys = true,keyProperty = "pid")
//    @Insert("INSERT into t_supplier (supplier_code,supplier_name,contact,phone,address,fax,description,create_date)" +
//            " values(#{supplierCode},#{supplierName},#{contact},#{phone},#{address},#{fax},#{description},#{createDate})")
    Integer    addSupplier(Supplier supplier);

    //删除供应商
//    @Delete("DELETE FROM t_supplier WHERE pid = #{pid}")
    Integer    deleteSupplierById(Integer sid);

    //修改供应商
//    @Update("UPDATE t_supplier SET supplier_code = #{supplierCode},supplier_name = #{supplierName},contact = #{contact}," +
//            "phone = #{phone},address = #{address},fax = #{fax},description = #{description},create_date = now()" +
//            "where pid = #{pid}")
    Integer    updateSupplier(Supplier supplier);


}
