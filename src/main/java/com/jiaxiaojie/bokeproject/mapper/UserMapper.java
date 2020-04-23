package com.jiaxiaojie.bokeproject.mapper;

import com.jiaxiaojie.bokeproject.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public  interface UserMapper {
    @Insert("insert into user(accountId,name,token,gmt_create,gmt_modified,avatar_url,bio,password) values(#{accountId},#{name},#{token},#{gmt_create},#{gmt_modified},#{avatar_url},#{bio},#{password})")
    void insert(User user);
    @Select("select *from user where token=#{token}")
    User findByToken(@Param("token") String token);
    @Select("select *from user")
    List<User> findAll();
    @Select("select *from user where accountId=#{accountId}")
    User findByAccountId(String accountId);
    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmt_modified},avatar_url=#{avatar_url} where id=#{id}")
    void update(User user);
    @Select("select *from user where accountId=#{accountId} and password=#{password}")
    User findByIdAndPsw(String accountId,String password);
    @Select("select *from user where name=#{searchname}")
    User findByName(String searchname);
}
