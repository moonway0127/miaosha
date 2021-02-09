package com.moonway;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.moonway.dao.UserDOMapper;
import com.moonway.dataobject.UserDO;

import com.moonway.response.CommonReturnType;
import org.apache.ibatis.io.Resources;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;


/**
 * Hello world!
 *
 */

@SpringBootApplication(scanBasePackages = {"com.moonway"})
@RestController
@MapperScan("com.moonway.dao")
public class App{

    @Autowired
    private HttpServletRequest httpServletRequest;


    @RequestMapping("/getotp")
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name ="mobile")String mobile){
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String optCode = String.valueOf(randomInt);

        httpServletRequest.getSession().setAttribute(mobile,optCode);
        System.out.println("本次code:"+optCode);

        return CommonReturnType.create(null);

    }


    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);

    }






}