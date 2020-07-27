package com.spring.tiger.vue.vueuserserver.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MybatisPlusConfig {


   /**
    * mybatis-plus分页插件<br>
    * 文档：http://mp.baomidou.com<br>
    */
   @Bean
   public PaginationInterceptor paginationInterceptor() {
      PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
      // 开启 PageHelper 的支持
      paginationInterceptor.setLocalPage(true);
      List<ISqlParser> sqlParserList = new ArrayList<>();
      paginationInterceptor.setSqlParserList(sqlParserList);
      return paginationInterceptor;
   }

   /**
    * 相当于顶部的：
    * {@code @MapperScan("com.baomidou.springboot.mapper*")}
    * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
    */
   @Bean
   public MapperScannerConfigurer mapperScannerConfigurer() {
      MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
      scannerConfigurer.setBasePackage("com.spring.tiger.vue.vueuserserver.dao*");
      return scannerConfigurer;
   }


}
