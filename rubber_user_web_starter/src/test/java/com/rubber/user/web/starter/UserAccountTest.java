package com.rubber.user.web.starter;

import com.rubber.user.dao.entity.UserAccountInfo;
import com.rubber.user.dao.logic.UserAccountInfoLogic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
@SpringBootTest
public class UserAccountTest {


    @Autowired
    private UserAccountInfoLogic userAccountInfoLogic;


    @Test
    void testCount() {
        UserAccountInfo accountInfo = userAccountInfoLogic.getByUid(100101);
        System.out.println(accountInfo);
    }



    @Test
    void addIndex() {
        UserAccountInfo accountInfo =  new UserAccountInfo();
        accountInfo.setUserAccount("value");
        userAccountInfoLogic.addUserAccount(accountInfo);
        System.out.println(accountInfo);
    }





}
