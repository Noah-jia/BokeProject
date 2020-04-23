package com.jiaxiaojie.bokeproject.service;

import com.jiaxiaojie.bokeproject.domain.Friends;
import com.jiaxiaojie.bokeproject.domain.User;
import com.jiaxiaojie.bokeproject.mapper.FriendsMapper;
import com.jiaxiaojie.bokeproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FriendsService {
    @Autowired
    private FriendsMapper friendsMapper;
    @Autowired
    private UserMapper userMapper;
    public List<User> findById(String accountId) {
        List<Friends> friends=friendsMapper.findById(accountId);
        List<User> users1=new ArrayList<>();
        for(Friends friends1:friends){
            User user=userMapper.findByAccountId(friends1.getFriend_id());
            users1.add(user);
            User user1=userMapper.findByAccountId(friends1.getUser_id());
            users1.add(user1);
        }
        return remove(users1);
    }
    public  List<User> remove(List<User> users){
        Iterator<User> iterator=users.iterator();
        List<User> newList=new ArrayList<>();
        while (iterator.hasNext()){
            User next=iterator.next();
            if(!newList.contains(next)){
                newList.add(next);
            }
        }
        return newList;
    }

    public void insert(String accountId, String searchAccountId) {
        friendsMapper.insert(accountId,searchAccountId);
    }

    public Friends findByUAF(String accountId, String searchAccountId) {
          return friendsMapper.findByUAF(accountId,searchAccountId);
    }
}
