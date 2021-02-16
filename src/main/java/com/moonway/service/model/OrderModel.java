package com.moonway.service.model;

import java.math.BigDecimal;

public class OrderModel {

    public OrderModel(){

    }

    public OrderModel(String id, BigDecimal itemPrice,
               Integer userId, Integer itemId,
               Integer amount,BigDecimal orderPrice){
        this.id = id;
        this.itemPrice = itemPrice;
        this.userId = userId;
        this.itemId = itemId;
        this.amount = amount;
        this.orderPrice = orderPrice;

    }

    public OrderModel(BigDecimal itemPrice,
               Integer userId, Integer itemId,
               Integer amount,BigDecimal orderPrice){

        this.itemPrice = itemPrice;
        this.userId = userId;
        this.itemId = itemId;
        this.amount = amount;
        this.orderPrice = orderPrice;

    }

    private String id;

    private BigDecimal itemPrice;
    private Integer userId;
    private Integer itemId;
    private Integer amount;
    private BigDecimal orderPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }


    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
}
