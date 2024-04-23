package com.example.ivcdancer.controller.user.account;


import com.example.ivcdancer.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/account/token/")
    public Map<String,String> getToken(@RequestBody Map<String,String> map){
        System.out.println(map);
        String name = map.get("username");
        String password = map.get("password");
        System.out.println(name);
        System.out.println(password);
        return loginService.getToken(name,password);
    }
}
