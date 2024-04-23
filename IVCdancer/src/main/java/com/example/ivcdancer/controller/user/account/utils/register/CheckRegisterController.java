package com.example.ivcdancer.controller.user.account.utils.register;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ivcdancer.mapper.UserMapper;
import com.example.ivcdancer.pojo.User;
import com.example.ivcdancer.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

public class CheckRegisterController {

        @Autowired
        private LoginService loginService;

        @Autowired
        private UserMapper userMapper;

        @PostMapping("/user/account/utils/checkregister/")
        public Map<String,Boolean> getToken(@RequestBody Map<String,String> map){
            Map<String, Boolean> res = new HashMap<>();
            String name = map.get("username");
            System.out.println(name);
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", name);
            List<User> users = userMapper.selectList(queryWrapper);
            if (!users.isEmpty()) {
                res.put("data", true);
            }  else {
                res.put("data", false);
            }
            return res;
        }
    }

