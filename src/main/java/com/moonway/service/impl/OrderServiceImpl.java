package com.moonway.service.impl;

import com.moonway.dao.OrderInfoDOMapper;
import com.moonway.dao.SequenceInfoDOMapper;
import com.moonway.dto.OrderInfoDO;
import com.moonway.dto.SequenceInfoDO;
import com.moonway.error.BusinessException;
import com.moonway.error.EmBusinessError;
import com.moonway.service.ItemService;
import com.moonway.service.OrderService;
import com.moonway.service.UserService;
import com.moonway.service.model.ItemModel;
import com.moonway.service.model.OrderModel;
import com.moonway.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderInfoDOMapper orderInfoDOMapper;

    @Autowired
    private SequenceInfoDOMapper sequenceInfoDOMapper;

    @Transactional
    @Override
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount,Integer promoId) throws BusinessException {
        //1.校验下单状态，下单是否存在，用户是否合法，购买数量是否正确
        ItemModel itemModel =  itemService.getItemById(itemId);
        if(itemModel ==null) throw  new BusinessException(EmBusinessError.ITEM_NOT_EXISTS);

        UserModel userModel = userService.getUserById(userId);
        if(userModel ==null) throw  new BusinessException(EmBusinessError.USER_NOT_EXISTS);

        if(amount<=0||amount>99) throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"数量信息不正确");

        //校验活动信息
        if (promoId!=null){
            //校验对应活动是否存在
            if(promoId.intValue()!=itemModel.getPromoModel().getId()){
                throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"活动信息不正确");

            }else if(itemModel.getPromoModel().getStatus().intValue()!=2){
                throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"活动未开始");
            }


        }

        //2.落单减库存
        if(!itemService.decreaseStock(itemId,amount)) throw new BusinessException(EmBusinessError.ITEM_STOCK_ENOUGH);

        //3.订单入库
        //生成交易订单号
        OrderModel orderModel = new OrderModel(null,userId,itemId,amount,null);

        if(promoId !=null){
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else {
            orderModel.setItemPrice(itemModel.getPrice());
        }

        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));
        orderModel.setId(generateOrderNo(userId));
        orderModel.setPromoId(promoId);
        OrderInfoDO orderInfoDO = convertFromOrderModel(orderModel);
        orderInfoDOMapper.insertSelective(orderInfoDO);
        itemService.increaseSales(itemId,amount);
        //4.返回前端
        return orderModel;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    private String generateOrderNo(Integer userId){
        StringBuilder stringBuilder = new StringBuilder();
        //8位时间信息+6位自增序列+2位分库分表位
        LocalDateTime now = LocalDateTime.now();
        String nowDate =  now.format(DateTimeFormatter.ISO_DATE).replace("-","");
        stringBuilder.append(nowDate);
        int sequence = 0;
        SequenceInfoDO sequenceInfoDO = sequenceInfoDOMapper.getSequenceByName("order_info");
        sequenceInfoDO.setCurrentValue(sequence = sequenceInfoDO.getCurrentValue()+sequenceInfoDO.getStep());
        sequenceInfoDOMapper.updateByPrimaryKeySelective(sequenceInfoDO);
        ;
        DecimalFormat df = new DecimalFormat("000000");
        stringBuilder.append(df.format(sequence));
        stringBuilder.append("00");
        return stringBuilder.toString();
    }



    private OrderInfoDO convertFromOrderModel(OrderModel orderModel){
        if(orderModel == null) return  null;

        OrderInfoDO orderInfoDO = new OrderInfoDO();
        BeanUtils.copyProperties(orderModel,orderInfoDO);
        orderInfoDO.setItemPrice(orderModel.getItemPrice().doubleValue());
        orderInfoDO.setOrderprice(orderModel.getOrderPrice().doubleValue());
        return orderInfoDO;
    }
}
