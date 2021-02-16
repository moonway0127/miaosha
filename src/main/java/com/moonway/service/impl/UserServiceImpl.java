package com.moonway.service.impl;

import com.moonway.dao.UserDOMapper;
import com.moonway.dao.UserPasswordDOMapper;
import com.moonway.dto.UserDO;
import com.moonway.dto.UserPasswordDO;
import com.moonway.error.BusinessException;
import com.moonway.error.EmBusinessError;
import com.moonway.service.UserService;
import com.moonway.service.model.UserModel;
import com.moonway.validator.ValidationResult;
import com.moonway.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;
    @Autowired
    private ValidatorImpl validator;
    @Override
    public UserModel getUserById(Integer id) {
      UserDO userDo =  userDOMapper.selectByPrimaryKey(id);
      if(userDo == null){
          return null;
      }
      UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDo.getId());
      return convertFromDataObject(userDo,userPasswordDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserModel userModel) throws BusinessException {

//        if(userModel==null||StringUtils.isEmpty(userModel.getName())||
//            userModel.getGender()==null || userModel.getAge()==null || StringUtils.isEmpty(userModel.getMobile())){
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//        }

        ValidationResult result = validator.validate(userModel);
        if(result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

        UserDO userDO = convertFromModel(userModel);
        convertFromModel(userModel);
        userDOMapper.insertSelective(userDO);
        userModel.setId(userDO.getId());
        UserPasswordDO userPasswordDO =  convertPwdFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);

    }

    @Override
    public UserModel login( UserModel userModel)  {

        UserDO userDO = userDOMapper.selectByMobile(userModel.getMobile());
        if(userDO == null ||userDO.getId() ==null||userDO.getId() == 0 ){
            return null;
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());

        if(userPasswordDO == null||!userPasswordDO.getEncrptPassword().equals(userModel.getEncrptPassword())) {
            return null;
        }
        return convertFromDataObject(userDO,userPasswordDO);


    }

    private UserPasswordDO convertPwdFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
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
