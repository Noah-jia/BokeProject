package com.jiaxiaojie.bokeproject.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.jiaxiaojie.bokeproject.domain.Friends;
import com.jiaxiaojie.bokeproject.domain.User;
import com.jiaxiaojie.bokeproject.service.FriendsService;
import com.jiaxiaojie.bokeproject.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.log4j.Log4j;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class FriendsController {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendsService friendsService;
    @GetMapping("/friendslist")
    public String friends(HttpServletRequest request,
                          Model model){
        Cookie[] cookies = request.getCookies();
        User user=null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userService.findByToken(token);
                }
            }
        }
         List<User>  friends=friendsService.findById(user.getAccountId());
         model.addAttribute("friends",friends);
            return "friends";
    }
    @RequestMapping(value = "/searchname")
    @ResponseBody
    public JSON searchname(
              @RequestParam("searchname2")String searchname,
              Model model,
              HttpServletRequest request
    ){
        User user=userService.findByName(searchname);
        if(user!=null) {
            model.addAttribute("searchAccountId", user.getAccountId());
        }
        JSON json= JSONObject.fromObject(user);
        return json;
    }
    @RequestMapping(value = "/addfriend")
    @ResponseBody
    public String addfinend(
            @RequestParam("searchAccountId")String searchName,
            HttpServletRequest request
    ){
        User user2=userService.findByName(searchName);
        Cookie[] cookies = request.getCookies();
        Friends friends=null;
        User user=null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userService.findByToken(token);
                }
            }
        }
        if(user2!=null) {
           friends = friendsService.findByUAF(user.getAccountId(), user2.getAccountId());
        }
        if(friends==null) {
            friendsService.insert(user.getAccountId(), user2.getAccountId());
            return "success";
        }
        else{
            return "false";
        }
    }
}

