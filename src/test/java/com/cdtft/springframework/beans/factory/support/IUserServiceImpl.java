package com.cdtft.springframework.beans.factory.support;

import java.util.Random;

/**
 * Test FactoryBean
 *
 * @author: wangcheng
 * @date: 2021年09月24 14:13
 */
public class IUserServiceImpl implements IUserService {

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "xiao wang，100001，深圳";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }
}
