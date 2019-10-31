package com.lkq.billmanagesystem;

        import org.mybatis.spring.annotation.MapperScan;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.lkq.billmanagesystem.dao")
@SpringBootApplication
public class BillManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillManageSystemApplication.class, args);
    }

}
