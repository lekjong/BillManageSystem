package com.lkq.billmanagesystem.controllers;


import com.lkq.billmanagesystem.dao.SupplierMapper;
import com.lkq.billmanagesystem.entities.Bill;
import com.lkq.billmanagesystem.entities.BillSupplier;
import com.lkq.billmanagesystem.entities.Supplier;
import com.lkq.billmanagesystem.services.BillService;
import com.lkq.billmanagesystem.services.SupplierService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author  lkq
 *
 * @function  用于响应账单操作的相关请求，处理账单数据，并将数据交给模板引擎。包括账单的crud操作；
 *
 *
 *
 */


@Controller
public class Billcontroller {

     @Autowired
     BillService  billService;

     @Autowired
     SupplierService supplierService;

     Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/bills")
    public String  getBills(Bill bill,Map<String,Object> map){

        map.put("billSupList",billService.getBills(bill));

        map.put("suppliers",supplierService.getSuppliers(null));

        //设置回显；
        map.put("billName",bill.getBillName());
        map.put("sid",bill.getSid());
        map.put("pay",bill.getPay());

        logger.info("pay的传递值为："+bill.getPay());

        return  "bill/list";
    }


    @RequiresPermissions("role:addBill")
    @GetMapping("/addBill.html")
    public String addBill(Map<String,Object> map){

        List<Supplier> suppliers = supplierService.getSuppliers(null);

        map.put("suppliers",suppliers);

        return "bill/add";
    }


    @PostMapping("/bill")
     public String addBill(Bill bill){

        billService.addBill(bill);

        return "redirect:/bills";
     }

     @RequiresPermissions("role:updateBill")
     @GetMapping("/bill/{bid}")
     public String toViewBill(@PathVariable(value = "bid")Integer bid, Map<String,Object> map,
                              @RequestParam(value = "type",defaultValue="view")String type){

         BillSupplier billS = billService.findBillByBid(bid);

         List<Supplier> suppliers = supplierService.getSuppliers(null);

         map.put("billS",billS);
         map.put("suppliers",suppliers);

         return "bill/"+type;
     }


     @PutMapping("/bill/{bid}")
     public String updateBill(@PathVariable(value = "bid")Integer bid, Bill bill){

         bill.setBid(bid);

         Integer integer = billService.updateBill(bill);

         return "redirect:/bills";
     }

     @RequiresPermissions("role:deleteBill")
     @DeleteMapping("/bill/{bid}")
     public String deleteBill(@PathVariable(value ="bid" )Integer bid ){

        billService.deleteBillByBid(bid);

        return "redirect:/bills";
     }


}
