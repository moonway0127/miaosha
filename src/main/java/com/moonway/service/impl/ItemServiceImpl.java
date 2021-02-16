package com.moonway.service.impl;

import com.moonway.dao.ItemStockDOMapper;
import com.moonway.dao.OrderItemDOMapper;
import com.moonway.dto.ItemStockDO;
import com.moonway.dto.OrderItemDO;
import com.moonway.error.BusinessException;
import com.moonway.error.EmBusinessError;
import com.moonway.service.ItemService;
import com.moonway.service.model.ItemModel;
import com.moonway.validator.ValidationResult;
import com.moonway.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;


    @Autowired
    private OrderItemDOMapper orderItemDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    @Transactional
    @Override
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
       ValidationResult result =  validator.validate(itemModel);
        if(result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }
        OrderItemDO orderItemDO = convertItemDOFromItemModel(itemModel);
        orderItemDOMapper.insertSelective(orderItemDO);
        if (orderItemDO.getId()==null) return null;
        itemModel.setId(orderItemDO.getId());
        ItemStockDO itemStockDO = convertItemStockDOFromItemModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);
        if (itemModel.getId()==null) return null;

        return getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {

        List<OrderItemDO> orderItemDOS =  orderItemDOMapper.selectAllItem();

        List<ItemModel> list = orderItemDOS.stream().map( itemDO ->{
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            return convertItemModelFromDO(itemDO,itemStockDO);
        }).collect(Collectors.toList());

        return list;
    }



    @Override
    public ItemModel getItemById(Integer id) {
        OrderItemDO orderItemDO = orderItemDOMapper.selectByPrimaryKey(id);
        if (orderItemDO == null) return null;

        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(orderItemDO.getId());
        if (itemStockDO == null) return null;


        return convertItemModelFromDO(orderItemDO,itemStockDO);
    }

    @Transactional
    @Override
    public boolean decreaseStock(Integer itemId, Integer amount) {

        return itemStockDOMapper.decreaseStock(itemId,amount)>0?true:false;
    }


    private ItemModel convertItemModelFromDO(OrderItemDO orderItemDO ,ItemStockDO itemStockDO){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(orderItemDO,itemModel);
        itemModel.setPrice(BigDecimal.valueOf(orderItemDO.getPrice()));
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }


    private OrderItemDO convertItemDOFromItemModel(ItemModel itemModel){
        if(itemModel==null) return null;
        OrderItemDO orderItemDO = new OrderItemDO();
        BeanUtils.copyProperties(itemModel,orderItemDO);
        orderItemDO.setPrice(itemModel.getPrice().doubleValue());
        return orderItemDO;
    }

    private ItemStockDO convertItemStockDOFromItemModel(ItemModel itemModel){
        if(itemModel == null) return  null;

        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());

        return itemStockDO;
    }



}
