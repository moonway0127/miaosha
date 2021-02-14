package com.moonway.controller;

import com.moonway.response.CommonReturnType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(allowCredentials = "true",origins = "*",allowedHeaders = "*")
@Controller("order")
@RequestMapping("/order")
public class OrderController extends BaseController{

    @RequestMapping("/createItem")
    private CommonReturnType createItem(){

    }


}
