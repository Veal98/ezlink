package cn.itmtx.ddd.ezlink.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.itmtx.ddd.ezlink.infrastructure.*.mapper")
public class MyBatisConfig {

}