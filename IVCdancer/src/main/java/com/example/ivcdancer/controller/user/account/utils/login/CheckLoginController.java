package com.example.ivcdancer.controller.user.account.utils.login;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ivcdancer.mapper.UserMapper;
import com.example.ivcdancer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CheckLoginController {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/user/account/utils/checklogin/")
    public Map<String,Boolean> checklogin(@RequestBody Map<String,String> map){
        Map<String, Boolean> res = new HashMap<>();
//        System.out.println(map);

        String username = map.get("username");
        String password = map.get("password");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(map.keySet());
        User user = userMapper.selectOne(queryWrapper);
        String enpassword = user.getPassword();
        if (passwordEncoder.matches(password, enpassword)) {
            res.put("data", true);
        } else {
            res.put("data", false);
        }
        return res;
    }
}
