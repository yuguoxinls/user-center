package com.jack.usercenter.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.usercenter.model.domain.User;
import com.jack.usercenter.service.UserService;
import com.jack.usercenter.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author yuguoxin
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2022-09-24 15:37:52
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    private static final String Salt = "yupi";
    private static final String USER_LOGIN_STATE = "userLoginState";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)){
            return -1;
        }
        if (userAccount.length()<4 || userPassword.length()<8 || checkPassword.length()<8){
            return -1;
        }
        String validPattern = "^.*[/^/$/.//,;:'!@#%&/*/|/?/+/(/)/[/]/{/}]+.*$";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return -1;
        }
        if (!userPassword.equals(checkPassword)){
            return -1;
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount, userAccount);

        long count = this.count(queryWrapper);
        if (count>0){
            return -1;
        }
        //2. 对密码进行加密
        String handledPassword = DigestUtils.md5DigestAsHex((Salt + userPassword).getBytes());
        //3. 插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(handledPassword);
        boolean saveResult = this.save(user);
        if (!saveResult){
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1. 检验信息是否合法 TODO: null改为统一的异常处理类
        if (StringUtils.isAnyBlank(userAccount, userPassword)){
            return null;
        }
        if (userAccount.length()<4 || userPassword.length()<8){
            return null;
        }
        String validPattern = "^.*[/^/$/.//,;:'!@#%&/*/|/?/+/(/)/[/]/{/}]+.*$";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return null;
        }
        //2. 校验密码是否输入正确，要和数据库中的密文密码去对比
        String handledPassword = DigestUtils.md5DigestAsHex((Salt + userPassword).getBytes());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount, userAccount);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            return null;
        }
        String password = user.getUserPassword();
        if (!password.equals(handledPassword)){
            log.info("login failed, useAccount does not match userPassword!");
            return null;
        }
        //3. 用户信息脱敏，隐藏敏感信息，防止数据库中的字段泄露
        User safetyUser = new User();
        safetyUser.setId(user.getId());
        safetyUser.setUserName(user.getUserName());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setGender(user.getGender());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setCreateTime(user.getCreateTime());
        //4. 记录用户登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        //5. 返回脱敏后的用户信息
        return safetyUser;
    }
}




