package com.jack.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.usercenter.model.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
* @author yuguoxin
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2022-09-24 15:37:52
*/
public interface UserService extends IService<User> {

    long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode);

    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User getSafetyUser(User originUser);

    int userLogout(HttpServletRequest request);
}
