package com.moonway.controller;


import com.moonway.error.BusinessException;
import com.moonway.error.EmBusinessError;
import com.moonway.response.CommonReturnType;
import com.moonway.service.OrderService;
import com.moonway.service.model.OrderModel;
import com.moonway.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
@Controller("order")
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    OrderService orderService;
    @Autowired
    HttpServletRequest httpServletRequest;

    //封装下单请求

    @RequestMapping(value = "/createorder",method = RequestMethod.POST,consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId") Integer itemId,
                                        @RequestParam(name = "amount") Integer amount,
                                        @RequestParam(name = "promoId",required = false) Integer promoId) throws BusinessException {

        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin.booleanValue())
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);


        //获取用户的登录信息
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");


        OrderModel orderModel = orderService.createOrder(userModel.getId(), itemId, amount,promoId);


        return CommonReturnType.create(orderModel);
    }


}
