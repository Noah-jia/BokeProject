package com.jiaxiaojie.bokeproject.mapper;

import com.jiaxiaojie.bokeproject.domain.Friends;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendsMapper {
    @Select("select *from friends where ((user_id=#{accountId}) or (user_id!=#{accountId} and friend_id=#{accountId}))")
    List<Friends> findById(String accountId);
    @Insert("insert into friends(user_id,friend_id) values(#{accountId},#{searchAccountId})")
    void insert(String accountId, String searchAccountId);
    @Select("select *from friends where ( (user_id=#{accountId} and friend_id=#{searchAccountId})or(user_id=#{searchAccountId} and friend_id=#{accountId})) ")
    Friends findByUAF(String accountId, String searchAccountId);
}
