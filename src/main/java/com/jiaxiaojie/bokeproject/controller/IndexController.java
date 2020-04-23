package com.jiaxiaojie.bokeproject.controller;

import com.github.pagehelper.PageInfo;
import com.jiaxiaojie.bokeproject.domain.BokeDto;
import com.jiaxiaojie.bokeproject.service.BokeService;
import com.jiaxiaojie.bokeproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    private static final Logger logger= LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private BokeService bokeService;
    @GetMapping("/")
    public ModelAndView index(HttpServletRequest request,
                              @RequestParam(name = "page" ,defaultValue = "1")Integer page,
                              @RequestParam(name = "size" ,defaultValue = "4")Integer size
                           ){
        ModelAndView mv=new ModelAndView();
        List<BokeDto>  list=bokeService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("index");
        logger.info("springboot 整合Log4j");
        return mv;
      }
      @PostMapping("/search")
     public String findByTitle(
             Model model,
             @RequestParam("search")String title,
             @RequestParam(name = "page" ,defaultValue = "1")int page,
             @RequestParam(name = "size" ,defaultValue = "4")int size
      ){
        List<BokeDto> list=bokeService.findByTitle(title,page,size);
        PageInfo pageInfo2=new PageInfo(list);
        model.addAttribute("pageInfo2",pageInfo2);
        return "bokesearch";
      }
}
