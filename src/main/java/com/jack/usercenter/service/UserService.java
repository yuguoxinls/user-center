package com.jack.usercenter.service;

import com.jack.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yuguoxin
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2022-09-24 15:37:52
*/
public interface UserService extends IService<User> {

    long userRegister(String userAccount, String userPassword, String checkPassword);
}
