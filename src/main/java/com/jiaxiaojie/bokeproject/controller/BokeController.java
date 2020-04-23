package com.jiaxiaojie.bokeproject.controller;

import com.jiaxiaojie.bokeproject.domain.Boke;
import com.jiaxiaojie.bokeproject.domain.User;
import com.jiaxiaojie.bokeproject.service.BokeService;
import com.jiaxiaojie.bokeproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class BokeController {
    @Autowired
    private UserService userService;
    @Autowired
    private BokeService bokeService;
    @GetMapping("/boke")
    public String boke(){
        return "publishboke";
    }
    @PostMapping("/boke")
    public String InsertBoke(
            @RequestParam(value = "title")String title,
            @RequestParam(value = "description")String description,
            HttpServletRequest request
    ){  String token=null;
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    token=cookie.getValue();
                  break;
                }
            }
            User user=userService.findByToken(token);
            Boke boke=new Boke();
            boke.setCreator(user.getAccountId());
            boke.setTitle(title);
            boke.setDescription(description);
            bokeService.Insert(boke);
        }
        return "redirect:/";
    }

}
