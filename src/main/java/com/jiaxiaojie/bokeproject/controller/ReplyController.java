package com.jiaxiaojie.bokeproject.controller;

import cn.hutool.log.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.jiaxiaojie.bokeproject.domain.ReplyDto;
import com.jiaxiaojie.bokeproject.domain.User;
import com.jiaxiaojie.bokeproject.service.BokeService;
import com.jiaxiaojie.bokeproject.service.ReplyService;
import com.jiaxiaojie.bokeproject.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ReplyController {
    @Autowired
    private UserService userService;
    @Autowired
    private BokeService bokeService;
    @Autowired
    private ReplyService replyService;
    @RequestMapping("/reply")
    @ResponseBody
    public JSONArray  reply(Model model,
                         @RequestParam("reply")String reply,
                         @RequestParam("bokeid")Integer id,
                         HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        User user = userService.findByToken(token);
        System.out.println("id为:"+id+"/n"+"cookie用户为："+user);
        ReplyDto list=null;
        if(user!=null) {
            replyService.insert(reply, user.getAccountId(), id);
            list = replyService.findByAandT(user.getAccountId(), id);
        }
            JSONArray json= JSONArray.fromObject(list);
            System.out.println("json为："+json);
            return  json;

    }
}
