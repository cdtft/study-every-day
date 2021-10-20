package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.annotation.Autowired;
import com.cdtft.springframework.stereotype.Component;

/**
 * @author: wangcheng
 * @date: 2021年08月04 17:23
 */
@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public String print() {
        return userDao.findByUserNameById(1);
    }

}
