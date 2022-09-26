package com.jack.usercenter.service;
import java.util.Date;

import com.jack.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户服务测试
 *
 * @author jack
 */

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testAddUser(){
        User user = new User();
        user.setUserName("jack");
        user.setUserAccount("123");
        user.setAvatarUrl("https://www.bing.com/ck/a?!&&p=c28eadc6119bd394JmltdHM9MTY2Mzk3NzYwMCZpZ3VpZD0xNDhjYTY5Ni04NmQ1LTY4YzYtMmZlYi1hOWUzODdmYjY5N2QmaW5zaWQ9NTQ2OQ&ptn=3&hsh=3&fclid=148ca696-86d5-68c6-2feb-a9e387fb697d&u=a1L2ltYWdlcy9zZWFyY2g_cT0lRTUlOUIlQkUlRTclODklODcmRk9STT1JUUZSQkEmaWQ9MUM4MjgwRDJENzVCODY1M0ZFNEY4NzgxNzM4N0Y1NzE4OUI1QUE0MQ&ntb=1");
        user.setUserPassword("123");
        user.setPhone("456");
        user.setEmail("789");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setGender(0);

        userService.save(user);

    }

    @Test
    void userRegister() {
        String userAccount = "jack";
        String userPassword = "";
        String checkPassword = "12345678";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "jac";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "jack";
        userPassword = "1234567";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "jack ";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "jack";
        checkPassword = "123";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "123";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
    }
}