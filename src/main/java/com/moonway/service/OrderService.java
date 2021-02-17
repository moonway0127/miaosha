package com.moonway.service;

import com.moonway.error.BusinessException;
import com.moonway.service.model.OrderModel;

public interface OrderService {
    OrderModel createOrder(Integer userId,Integer itemId,Integer amount,Integer promoId) throws BusinessException;

}
