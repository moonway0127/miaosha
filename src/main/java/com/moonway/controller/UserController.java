package com.moonway.controller;

import com.alibaba.druid.util.StringUtils;
import com.moonway.controller.viewobject.UserVO;
import com.moonway.error.BusinessException;
import com.moonway.error.EmBusinessError;
import com.moonway.response.CommonReturnType;
import com.moonway.service.UserService;
import com.moonway.service.impl.UserServiceImpl;
import com.moonway.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLEngineResult;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@CrossOrigin()
@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /***
     * 根据id获取用户信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id")Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);
        if(userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXISTS);
        }
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }


    /***
     * 获取验证码
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/getotp",method = RequestMethod.POST,consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name ="mobile")String mobile){
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String optCode = String.valueOf(randomInt);
        httpServletRequest.getSession().setAttribute(mobile,optCode);
        System.out.println("本次code:"+optCode);
        return CommonReturnType.create(null);

    }


    @RequestMapping(value = "/regist")
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "mobile")String mobile,
                                     @RequestParam(name = "otpCode")String otpCode,
                                     @RequestParam(name = "name")String name,
                                     @RequestParam(name = "gender")Byte gender,
                                     @RequestParam(name = "age")Integer age
                                     ) throws BusinessException {
        //验证手机号与otpCode

        String sessionOtpCode = (String) httpServletRequest.getSession().getAttribute(mobile);
        if(StringUtils.equals(sessionOtpCode,otpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不正确");
        }
        UserModel userModel = new UserModel();
        userModel.setAge(age);
        userModel.setMobile(mobile);
        userModel.setName(name);
        userModel.setGender(gender);
        userService.register(userModel);

        return CommonReturnType.create("success");
    }





    public UserVO convertFromModel(UserModel userModel){
        if(userModel == null){

            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }


    @Override
    Object handlerException(HttpServletRequest request, Exception ex) {
       return super.handlerException(request,ex);
    }
}
