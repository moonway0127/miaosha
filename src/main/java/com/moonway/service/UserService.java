package com.moonway.service;

import com.moonway.error.BusinessException;
import com.moonway.service.model.UserModel;

public interface UserService {
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

}
