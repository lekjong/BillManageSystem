package com.lkq.billmanagesystem.controllers;

import com.lkq.billmanagesystem.entities.Supplier;
import com.lkq.billmanagesystem.serviceImps.SupplierServiceImp;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@Controller
public class SupplierController {

    Logger logger = LoggerFactory.getLogger(getClass());

    //该类由框架创建，暂时不存在；
    @Autowired
    private SupplierServiceImp supplierService;


    @GetMapping("/suppliers")
    public String  getSuppliers(Supplier supplier, Map<String,Object> map){

        List<Supplier> list  = supplierService.getSuppliers(supplier);

        map.put("supplierList",list);
        //返回查询的key用于回显；
        map.put("searKey",supplier.getSupplierName());

        return  "supplier/list";
    }


    /**
     *
     * @param pid
     * @param type  当view页面与update页面的请求形式一致时，传入的区分RequestParam
     * @param map
     * @return
     */
    @RequiresPermissions("role:updateSupplier")
    @GetMapping("/supplier/{pid}")
    public String findSupplierByPid(@PathVariable Integer pid,
                                    @RequestParam(value = "type",defaultValue = "view") String type,
                                    Map<String,Object> map){

        Supplier supplier  = (Supplier) supplierService.findSupplierBySid(pid);

        map.put("supplier",supplier);

        return  "supplier/"+type;
    }

    @RequiresPermissions("role:addSupplier")
    @GetMapping("/addSupplier.html")
    public String toAddSupplier(){

     return  "supplier/add";
    }


    @PostMapping("/supplier")
    public String  addSupplier(Supplier supplier){

        supplierService.addSupplier(supplier);

        //这里必须重定向，发送请求刷新更新的数据；
        return  "redirect:/suppliers";
        // return "supplier/list";
    }


    /**
     * @func  update操作先用带路径变量的get请求查询出对应的id的数据，再传到update页面，使用单一的无参put请求
     *
     * @param pid 必须传递pid
     * @param supplier
     * @return
     */
    @PutMapping("/supplier/{pid}")
    public  String updateSupplier(@PathVariable(value = "pid") int pid, Supplier supplier){

             supplier.setSid(pid);

            supplierService.updateSupplier(supplier);

        return   "redirect:/suppliers";

    }
    @RequiresPermissions("role:deleteSupplier ")
    @DeleteMapping("/supplier/{pid}")
    public String deleteSupplier(@PathVariable int pid){

        supplierService.deleteSupplierById(pid);

        return "redirect:/suppliers";
    }

}
