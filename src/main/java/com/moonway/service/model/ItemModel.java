package com.moonway.service.model;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemModel {

    public ItemModel(){
    }

    public ItemModel(String title, BigDecimal price, Integer stock, String description, String imgUrl, @Nullable Integer sales){
        this.title = title;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.sales = sales;
        this.ImgUrl = imgUrl;

    }

    private Integer id;


    @NotBlank(message = "商品名不能为空")
    private String title;

    @NotNull(message = "价格不能为空")
    @Min(value = 0 , message = "商品价格不能为0")
    private BigDecimal price;

    @NotNull(message = "库存不能为空")
    private Integer stock;

    @NotBlank(message = "描述不能为空")
    private String description;

    private Integer sales;

    @NotBlank(message = "图片连接不能为空")
    private String ImgUrl;


    private PromoModel promoModel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }


    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
    }
}
