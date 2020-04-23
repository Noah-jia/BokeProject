package com.jiaxiaojie.bokeproject.controller;

import com.jiaxiaojie.bokeproject.domain.User;
import com.jiaxiaojie.bokeproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/myInfo")
    public String myInfo(){
        return "myInfo";
    }
    @GetMapping("/logout")
    public String logout(
            HttpServletRequest request,
            HttpServletResponse response
    ){
       request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
    @GetMapping("/userInfo")
    public  String userInfo(HttpServletRequest request,Model model){
      String  accoutId=request.getParameter("replyId");
       User user=userService.findByAccoutId(accoutId);
       if(user!=null){
           model.addAttribute("userInfo",user);
       }
       return "myInfo";
    }
}
