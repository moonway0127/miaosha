package com.moonway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;


/**
 * Hello world!
 *
 */

@SpringBootApplication(scanBasePackages = {"com.moonway"})
@RestController
@MapperScan("com.moonway.dao")
@EnableTransactionManagement
public class App{




    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);


    }






}
