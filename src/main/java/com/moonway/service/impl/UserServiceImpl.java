package com.moonway.service.impl;

import com.moonway.dao.UserDOMapper;
import com.moonway.dao.UserPasswordDOMapper;
import com.moonway.dataobject.UserDO;
import com.moonway.dataobject.UserPasswordDO;
import com.moonway.error.BusinessException;
import com.moonway.error.EmBusinessError;
import com.moonway.service.UserService;
import com.moonway.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;
    @Override
    public UserModel getUserById(Integer id) {
      UserDO userDo =   userDOMapper.selectByPrimaryKey(id);
      if(userDo == null){
          return null;
      }
      UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDo.getId());
      return convertFromDataObject(userDo,userPasswordDO);
    }

    @Override
    public void register(UserModel userModel) throws BusinessException {

        if(userModel==null||StringUtils.isEmpty(userModel.getName())||
            userModel.getGender()==null || userModel.getAge()==null || StringUtils.isEmpty(userModel.getMobile())){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserDO userDO = convertFromModel(userModel);
        userDOMapper.insertSelective(userDO);
    }

    private UserDO convertFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }


    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        if (userDO == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        if (userPasswordDO !=null){
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }
        return userModel;
    }
}
