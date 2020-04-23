package com.jiaxiaojie.bokeproject.controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;

import com.jiaxiaojie.bokeproject.domain.User;
import com.jiaxiaojie.bokeproject.service.UserService;
import com.sun.deploy.security.SelectableSecurityManager;
import jdk.nashorn.internal.parser.Token;
import kotlin.reflect.KFunction;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String longin() {
        return "login";
    }

    @RequestMapping("/login.do")
    @ResponseBody
    public Object longin2(@RequestParam( "logname2")String accountId) {
        User user;
        if (accountId != null) {
            user = userService.findByAccoutId(accountId);
            System.out.println(user);
            if (user != null) {
                return "success";
            } else {
                return "false";
            }
        }
           else
               return "false";
        }
   @RequestMapping("/login2.do")
    @ResponseBody
    public Object longin3(@RequestParam( "logname2")String accountId,
                          @RequestParam( "logpassword2")String password,
                          HttpServletResponse response)
    {
        User user;
        if(accountId!=null&&password!=null){
            user = userService.findByIdAndPsw(accountId, password);
            if(user!=null) {
                response.addCookie(new Cookie("token",user.getToken()));
                return "success";
            }
            else {
                return "false";
            }
        }
        else
            return "false";

    }
}


      /*  User user = userService.findByAccountId(accountId);
        String token = user.getToken();
        response.addCookie(new Cookie("token", token));
        return "redirect:/";8?
    }

    @RequestMapping(value = "/buy/pay2", method = RequestMethod.POST)
    @ResponseBody
    public String find(@RequestParam("logname") String accountId,
                        @RequestParam("logpassword") String password,
                       HttpServletRequest request) {
        String token=null;
        User user = userService.findByAccountId(accountId);
        if(user!=null){
           token=user.getToken();
            System.out.println(token);
           return token;
        }
        return null;
    }
}*/
