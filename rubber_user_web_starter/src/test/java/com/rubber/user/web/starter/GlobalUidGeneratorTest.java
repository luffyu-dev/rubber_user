package com.rubber.user.web.starter;

import com.rubber.user.dao.logic.GlobalUidGeneratorLogic;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
@SpringBootTest
public class GlobalUidGeneratorTest {


    @Resource
    private GlobalUidGeneratorLogic globalUidGeneratorLogic;


    @Test
    public void test(){
        int uid = globalUidGeneratorLogic.createGlobalUid();
        System.out.println(uid);

    }




}
