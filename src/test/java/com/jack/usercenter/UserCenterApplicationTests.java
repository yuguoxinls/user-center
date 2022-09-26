package com.jack.usercenter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@SpringBootTest
class UserCenterApplicationTests {

    @Test
    void contextLoads() {
    }



    @Test
    public void testSelect() throws NoSuchAlgorithmException {
        String newPassword = DigestUtils.md5DigestAsHex(("yupi" + "abcd").getBytes());
        System.out.println(newPassword);
    }

}
