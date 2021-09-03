package com.cdtft.springframework.beans.factory.support;

/**
 * @author: wangcheng
 * @date: 2021年08月04 17:23
 */
public class UserService {

    private UserDao userDao;

    private Integer uId;

    public void sayHi() {
        System.out.println("hi!");
    }

    public void printName() {
        System.out.println(userDao.findByUserNameById(uId));
    }

    public void printUid() {
        System.out.println(uId);
    }

}
