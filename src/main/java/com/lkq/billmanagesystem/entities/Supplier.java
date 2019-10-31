package com.lkq.billmanagesystem.entities;

import java.util.Date;


/**
 * 供应商实体类
 * @Title: Interger
 * @Description: springboot.entities
 * @Auther: lkq
 * @Version: 1.0
 */
public class Supplier {

        private Integer sid;
        //供应商编码
        private String supplierCode;
        //供应商名称
        private String supplierName;
        //联系人
        private String contact;
        //联系电话
        private String phone;
        //联系地址
        private String address;
        //传真
        private String fax;
        //描述
        private String description;
        // 创建时间
        private Date createDate;

        public Supplier() {
        }

        public Supplier(Integer sid, String supplierCode, String supplierName, String contact, String phone, String address, String fax, String description) {
            this.sid = sid;
            this.supplierCode = supplierCode;
            this.supplierName = supplierName;
            this.contact = contact;
            this.phone = phone;
            this.address = address;
            this.fax = fax;
            this.description = description;
            this.createDate = new Date();
        }

        @Override
        public String toString() {
            return "Supplier{" +
                    "pid=" + sid +
                    ", supplierCode='" + supplierCode + '\'' +
                    ", supplierName='" + supplierName + '\'' +
                    ", contact='" + contact + '\'' +
                    ", phone='" + phone + '\'' +
                    ", address='" + address + '\'' +
                    ", fax='" + fax + '\'' +
                    ", description='" + description + '\'' +
                    ", createDate=" + createDate +
                    '}';
        }


    public Integer getSid() {
        return sid;
    }

            public void setSid(Integer sid) {
                this.sid = sid;
            }

            public String getSupplierCode() {
                return supplierCode;
            }

            public void setSupplierCode(String supplierCode) {
                this.supplierCode = supplierCode;
            }

            public String getSupplierName() {
                return supplierName;
            }

            public void setSupplierName(String supplierName) {
                this.supplierName = supplierName;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getFax() {
                return fax;
            }

            public void setFax(String fax) {
                this.fax = fax;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Date getCreateDate() {
                return createDate;
            }

            public void setCreateDate(Date createDate) {
                this.createDate = createDate;
            }

}


