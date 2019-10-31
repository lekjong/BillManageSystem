package com.lkq.billmanagesystem.myUtils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Util {

    public static void main(String[] args) {

        Md5Hash md5Hash = new Md5Hash("123456","lek",1);

        System.out.println(md5Hash.toString());


    }

}
