package com.jiaxiaojie.bokeproject.mapper;

import com.jiaxiaojie.bokeproject.domain.Reply;
import com.jiaxiaojie.bokeproject.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ReplyMapper {
    @Insert("insert into reply(content,authorId,targetId) values(#{reply},#{cookieId},#{targetId})")
    void insert(String reply,String cookieId,Integer targetId);
     @Select("select *from reply where  targetId=#{targetId} ")
    List<Reply> findAll(Integer targetId);
     @Select("select *from reply where authorId=#{authorId}")
    List<User> findByAuthor(String authorId);
    @Select("select *from reply where targetId=#{targetId}")
    User findByTarget(String targetId);
    @Select("select *from reply where id in(select max(id) from reply where targetId=#{targetId} and authorId=#{authorId})")
    Reply findByAandT(String authorId,Integer targetId);
}
