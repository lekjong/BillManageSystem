package com.lkq.billmanagesystem;

import com.lkq.billmanagesystem.dao.BillMapper;
import com.lkq.billmanagesystem.dao.PermissionMapper;
import com.lkq.billmanagesystem.dao.UserMapper;
import com.lkq.billmanagesystem.entities.Bill;
import com.lkq.billmanagesystem.entities.BillSupplier;
import com.lkq.billmanagesystem.entities.Permission;
import com.lkq.billmanagesystem.entities.User;
import com.lkq.billmanagesystem.serviceImps.SupplierServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillManageSystemApplicationTests {
    @Autowired
    SupplierServiceImp supplierService;

    Logger logger = LoggerFactory.getLogger(getClass());


//    @Test
//    public void contextLoads() throws Exception{
//
//
//        supplier1.setSupplierName("B货");
//      //  supplier1.setPid(8);
//
//        List<Interger> suppliers = supplierService.getSuppliers(supplier1);
//       logger.info(suppliers.toString());
//    }

     @Autowired
    BillMapper  billMapper;

    @Test
    public void testBill() throws Exception{

        BillSupplier billSupplier = new BillSupplier();
                     billSupplier.setBid(6);
                     billSupplier.setSid(null);
                     billSupplier.setBillName("s域名???");
                   //  billSupplier.setPay(1);

      //  List<Bill> bills = billMapper.getBills(billSupplier);
        Bill bill= billMapper.findBillByBid(5);
        //     billMapper.addBill(billSupplier);
          //   billMapper.updateBill(billSupplier);
          //   billMapper.deleteBillByBid(6);
        System.out.println(bill);


    }

    @Autowired
    UserMapper  userMapper;

    @Test
    public void testUser(){

        User user = new User(null,"Bob","Bob","123456",1,2);
       // List<User> users = userMapper.getUsers(null);
       //User user= userMapper.findUserById(1);
        user.setId(5);
        user.setBirthday(new Date("1993/12/02"));
       //  userMapper.updateUser(user);
      //  userMapper.deleteUserById(5);
    //  userMapper.addUser(user);


       // System.out.println(user);

        System.out.println(userMapper.findUserByUserName("root"));



    }

   @Autowired
   PermissionMapper permissionMapper;

    @Test
    public void testPermission(){

//        List<Permission> permissions= permissionMapper.findPermissionByUserId(1);
//        System.out.println(permissions.toString());
    }



}
