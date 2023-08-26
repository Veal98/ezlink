package cn.itmtx.ddd.ezlink.component.dl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.itmtx.ddd.ezlink", "com.alibaba.cola"})
public class DlApplication {

    public static void main(String[] args) {
        SpringApplication.run(DlApplication.class, args);
    }
}
