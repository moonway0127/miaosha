package com.moonway.service;

import com.moonway.error.BusinessException;
import com.moonway.service.model.ItemModel;

import java.util.List;

public interface ItemService {
    //创建商品

    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    //商品列表浏览
    List<ItemModel> listItem();

    //商品详情浏览

    ItemModel getItemById(Integer id);


    boolean decreaseStock(Integer itemId,Integer amount);

    void increaseSales(Integer itemId,Integer amount);

}
