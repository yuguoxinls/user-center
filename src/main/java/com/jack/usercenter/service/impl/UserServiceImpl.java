package com.jack.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.usercenter.model.domain.User;
import com.jack.usercenter.service.UserService;
import com.jack.usercenter.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author yuguoxin
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2022-09-24 15:37:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

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
        final String Salt = "yupi";
        String handledPassword = DigestUtils.md5DigestAsHex((Salt + "abcd").getBytes());
        //3. 插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(handledPassword);
        boolean saveResult = this.save(user);
        if (saveResult){
            return -1;
        }
        return user.getId();
    }
}




