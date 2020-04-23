package com.jiaxiaojie.bokeproject.service;

import com.github.pagehelper.PageHelper;
import com.jiaxiaojie.bokeproject.domain.Boke;
import com.jiaxiaojie.bokeproject.domain.Reply;
import com.jiaxiaojie.bokeproject.domain.ReplyDto;
import com.jiaxiaojie.bokeproject.domain.User;
import com.jiaxiaojie.bokeproject.mapper.BokeMapper;
import com.jiaxiaojie.bokeproject.mapper.ReplyMapper;
import com.jiaxiaojie.bokeproject.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyService {
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BokeMapper bokeMapper;
    public void insert(String reply,String cookieId,Integer targetId)
    {
        replyMapper.insert(reply,cookieId,targetId);
    }

    public List<ReplyDto> findAll(Integer targetId,Integer page,Integer size) {
        PageHelper.startPage(page,size);
        List<Reply> replyList=replyMapper.findAll(targetId);
        List<ReplyDto> replyDtoList=new ArrayList<>();
        for(Reply reply:replyList){
            User aUser=userMapper.findByAccountId(reply.getAuthorId());
            //找到当前问题
            Boke  boke=bokeMapper.findBytrueId(reply.getTargetId());
            User  tUser=userMapper.findByAccountId(boke.getCreator());
            ReplyDto replyDto=new ReplyDto();
            BeanUtils.copyProperties(reply,replyDto);
            replyDto.setAuthor(aUser);
            replyDto.setTarget(tUser);
            replyDtoList.add(replyDto);
        }
        return replyDtoList;

    }
    @Transactional
    public ReplyDto findByAandT(String authorId,Integer targetId){
        Reply reply=replyMapper.findByAandT(authorId,targetId);
        ReplyDto replyDto=new ReplyDto();
        BeanUtils.copyProperties(reply,replyDto);
        replyDto.setAuthor(userMapper.findByAccountId(reply.getAuthorId()));
        Boke  boke=bokeMapper.findBytrueId(reply.getTargetId());
        User  tUser=userMapper.findByAccountId(boke.getCreator());
        replyDto.setTarget(tUser);
        return replyDto;
    }
}
