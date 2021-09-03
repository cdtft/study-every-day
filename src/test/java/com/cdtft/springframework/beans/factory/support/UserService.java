package com.cdtft.springframework.beans.factory.support;

/**
 * @author: wangcheng
 * @date: 2021年08月04 17:23
 */
public class UserService {

    private UserDao userDao;

    private Integer userId;

    public void sayHi() {
        System.out.println("hi!");
    }

    public void printName() {
        System.out.println(userDao.findByUserNameById(userId));
    }

    public void printUid() {
        System.out.println(userId);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
