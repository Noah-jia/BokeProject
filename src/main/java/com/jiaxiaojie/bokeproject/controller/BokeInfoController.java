package com.jiaxiaojie.bokeproject.controller;

import com.github.pagehelper.PageInfo;
import com.jiaxiaojie.bokeproject.domain.BokeDto;
import com.jiaxiaojie.bokeproject.domain.ReplyDto;
import com.jiaxiaojie.bokeproject.service.BokeService;
import com.jiaxiaojie.bokeproject.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BokeInfoController {
    @Autowired
    private BokeService bokeService;
    @Autowired
    private ReplyService replyService;
    @GetMapping("/bokeinfo/{id}")
    public String bokeinfo(@PathVariable(name="id")Integer id,
                           @RequestParam(value = "page",defaultValue = "1")Integer page,
                           @RequestParam(value = "size",defaultValue = "3")Integer size,
                           Model model){
        BokeDto bokeDto=bokeService.findById(id);
        List<ReplyDto> list=replyService.findAll(id,page,size);
        PageInfo pageInfo=new PageInfo(list);
        model.addAttribute("pagereplylist",pageInfo);
        model.addAttribute("bokedto",bokeDto);
        return "bokeinfo";
    }

}
