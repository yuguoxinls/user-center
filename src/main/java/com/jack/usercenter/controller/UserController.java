package com.jack.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jack.usercenter.model.domain.User;
import com.jack.usercenter.model.domain.request.UserLoginRequest;
import com.jack.usercenter.model.domain.request.UserRegisterRequest;
import com.jack.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.jack.usercenter.constant.UserConstant.ADMIN_ROLE;
import static com.jack.usercenter.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest == null){
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)){
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if (userLoginRequest == null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)){
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);
    }

    @GetMapping("/search")
    public List<User> searchUsers(String username, HttpServletRequest request){
        boolean flag = isAdmin(request);
        if (!flag){
            return new ArrayList<>();
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            queryWrapper.like(User::getUserName, username);
        }
        return userService.list(queryWrapper);
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody int id, HttpServletRequest request){
        boolean flag = isAdmin(request);
        if (!flag){
            return false;
        }
        if (id<=0){
            return false;
        }
        return userService.removeById(id);
    }

    /**
     * 是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }



}
