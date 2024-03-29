package com.forest.tiger.rtemplateroot.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

   /**
    * 相当于顶部的：
    * {@code @MapperScan("com.baomidou.springboot.mapper*")}
    * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
    */
   @Bean
   public MapperScannerConfigurer mapperScannerConfigurer() {
      MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
      scannerConfigurer.setBasePackage("com.forest.tiger.rtemplateroot.dao*");
      return scannerConfigurer;
   }

}
