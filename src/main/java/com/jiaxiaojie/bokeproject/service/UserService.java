package com.jiaxiaojie.bokeproject.service;

import com.jiaxiaojie.bokeproject.domain.User;
import com.jiaxiaojie.bokeproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public void createOrUpdate(User user) {
         User dbUser=userMapper.findByAccountId(user.getAccountId());
        if(dbUser==null){
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
        }else{
            dbUser.setGmt_modified(System.currentTimeMillis());
            dbUser.setAvatar_url(user.getAvatar_url());
            dbUser.setToken(user.getToken());
            dbUser.setName(user.getName());
            userMapper.update(dbUser);
        }
    }

    public void insert(User user) {
        userMapper.insert(user);
    }
    public User findByToken(String token){
        User user=userMapper.findByToken(token);
         return user;
    }


    public User findByIdAndPsw(String accountId, String passworod) {
        return userMapper.findByIdAndPsw(accountId,passworod);
    }

    public User findByAccoutId(String accountId) {
        return userMapper.findByAccountId(accountId);
    }

    public User findByName(String searchname) {
        return userMapper.findByName(searchname);
    }
}
