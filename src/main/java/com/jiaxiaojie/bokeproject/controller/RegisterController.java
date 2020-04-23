package com.jiaxiaojie.bokeproject.controller;

import com.jiaxiaojie.bokeproject.domain.User;
import com.jiaxiaojie.bokeproject.service.UserService;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.CookieStore;
import java.util.UUID;

@Controller
public class RegisterController {
    @Value("${pathConfig-photoPath}")
    private String photoPath;
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping("/doregister")
    @ResponseBody
    public String doregister(
            @RequestParam(name = "name")String name,
            @RequestParam(name = "password")String password,
            @RequestParam(name ="accountId")String accountId,
           // @RequestParam(name = "image") MultipartFile multipartFile,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model
    ) throws FileNotFoundException {
        Cookie[] cookies=request.getCookies();
        if(cookies==null) {
            User user = new User();
            if(name!=null&&password!=null&&accountId!=null) {
                user.setName(name);
                String token = UUID.randomUUID().toString();
                user.setToken(token);
                user.setAccountId(accountId);
                user.setGmt_create(System.currentTimeMillis());
                user.setGmt_modified(user.getGmt_create());
                user.setPassword(password);
                user.setBio("1");
                // if(multipartFile!=null){
                // savePicture(multipartFile);
                //  }
                //user.setAvatar_url(multipartFile.getOriginalFilename());
                userService.insert(user);
                response.addCookie(new Cookie("token", token));
            }
            return "success";
        }else {
            return "false";
        }
    }
    @RequestMapping("/registername")
    @ResponseBody
    public String registername(@RequestParam("name")String name){
        if(name.equals("")){
            return "null";
        }
        User user=userService.findByName(name);
        if(user==null)
            return "success";
         else
             return "false";
    }
    @RequestMapping("/registerId")
    @ResponseBody
    public String registeraccount(@RequestParam("accountId")String accountId){
        if(accountId.equals("")){
            return "null";
        }
        User user=userService.findByName(accountId);
        if(user==null)
            return "success";
        else
            return "false";
    }






    public  void savePicture(MultipartFile multipartFile) throws FileNotFoundException {
        OutputStream os=null;
        InputStream inputStream=null;
        String fileName=null;
        try{
            inputStream=multipartFile.getInputStream();
            fileName=multipartFile.getOriginalFilename();
            String path=photoPath;
            byte[] bs=new byte[1024];
            int len;
            File tempFile=new File(path);
            if(!tempFile.exists()){
                tempFile.mkdir();
            }
            os=new FileOutputStream(tempFile.getPath()+File.separator+fileName);
            while((len=inputStream.read(bs))!=-1){
                os.write(bs,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
                inputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
