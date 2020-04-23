package com.jiaxiaojie.bokeproject.service;

import com.github.pagehelper.PageHelper;
import com.jiaxiaojie.bokeproject.domain.Boke;
import com.jiaxiaojie.bokeproject.domain.BokeDto;
import com.jiaxiaojie.bokeproject.domain.User;
import com.jiaxiaojie.bokeproject.mapper.BokeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BokeService {
    @Autowired
    BokeMapper bokeMapper;

    public void Insert(Boke boke) {
        bokeMapper.Insert(boke);
    }

    public List<BokeDto> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<Boke> bokes = bokeMapper.findAll();
        List<BokeDto> bokeDtos = new ArrayList<>();
        for (Boke boke : bokes) {
            User user = bokeMapper.findByCreate(boke.getCreator());
            BokeDto bokeDto = new BokeDto();
            BeanUtils.copyProperties(boke, bokeDto);
            bokeDto.setUser(user);
            bokeDtos.add(bokeDto);
        }
        return bokeDtos;
    }

    public List<BokeDto> findByTitle(String title, int page, int size) {
        PageHelper.startPage(page, size);
        List<Boke> bokes = bokeMapper.findByTitle(title);
        List<BokeDto> bokeDtos = new ArrayList<>();
        for (Boke boke : bokes) {
            User user = bokeMapper.findByCreate(boke.getCreator());
            BokeDto bokeDto = new BokeDto();
            BeanUtils.copyProperties(boke, bokeDto);
            bokeDto.setUser(user);
            bokeDtos.add(bokeDto);
        }
        return bokeDtos;
    }
    public BokeDto findById(Integer id){
        return bokeMapper.findById(id);
    }
}
