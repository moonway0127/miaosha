package com.moonway.controller;

import com.moonway.controller.vo.ItemVO;
import com.moonway.dto.OrderItemDO;
import com.moonway.error.BusinessException;
import com.moonway.error.EmBusinessError;
import com.moonway.response.CommonReturnType;
import com.moonway.service.ItemService;
import com.moonway.service.impl.ItemServiceImpl;
import com.moonway.service.model.ItemModel;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(allowCredentials = "true",origins = "*",allowedHeaders = "*")
@Controller("item")
@RequestMapping("/item")
public class ItemController extends BaseController{

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/createItem",method = RequestMethod.POST,consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    private CommonReturnType createItem(@RequestParam(name = "title")String title,
                                        @RequestParam(name = "price") BigDecimal price,
                                        @RequestParam(name = "stock")Integer stock,
                                        @RequestParam(name = "description")String description,
                                        @RequestParam(name = "imgUrl")String imgUrl
                                        ) throws BusinessException {
        return CommonReturnType.create(convertItemVOFromModel(itemService.createItem(new ItemModel(title,price,stock,description,imgUrl,null))));
    }


    @RequestMapping("/getitem")
    @ResponseBody
    private CommonReturnType getItem(@RequestParam(name = "id")Integer id) throws BusinessException {
        ItemModel itemModel = itemService.getItemById(id);
        if (itemModel==null||itemModel.getId()==null) throw new BusinessException(EmBusinessError.ITEM_NOT_EXISTS);
        return CommonReturnType.create(convertItemVOFromModel(itemModel));
    }

    @RequestMapping("/getAllItem")
    @ResponseBody
    private CommonReturnType getAllItem(){
        List<ItemVO> itemVOS = itemService.listItem().stream().map(item -> convertItemVOFromModel(item)
        ).collect(Collectors.toList());
        return CommonReturnType.create(itemVOS);
    }


    private ItemVO convertItemVOFromModel(ItemModel itemModel){
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel,itemVO);
        if(itemModel.getPromoModel()!=null){
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setPromoId(itemModel.getPromoModel().getId());
            itemVO.setStartTime(itemModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:SS")));
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else {
            itemVO.setPromoStatus(0);
        }
        return itemVO;

    }




}
