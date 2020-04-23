package com.jiaxiaojie.bokeproject.mapper;

import com.jiaxiaojie.bokeproject.domain.Boke;
import com.jiaxiaojie.bokeproject.domain.BokeDto;
import com.jiaxiaojie.bokeproject.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BokeMapper {
    @Insert("insert into pquestion(title,description,creator) values(#{title},#{description},#{creator})")
    void Insert(Boke boke);
    @Select("select *from pquestion")
    List<Boke> findAll();
    @Select("select *from user where accountId=#{creator}")
    User findByCreate(String creator);
    @Select("select *from pquestion where title   like concat('%','${title}','%')")
    List<Boke> findByTitle(@Param("title") String title);
    @Select("select *from pquestion where id=#{id}")
    BokeDto findById(Integer id);
    @Select("select *from pquestion where id=#{id}")
    Boke findBytrueId(Integer id);
}
