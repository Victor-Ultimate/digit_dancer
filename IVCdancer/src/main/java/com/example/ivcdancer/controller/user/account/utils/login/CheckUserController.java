package com.example.ivcdancer.controller.user.account.utils.login;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ivcdancer.mapper.UserMapper;
import com.example.ivcdancer.pojo.User;
import com.example.ivcdancer.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CheckUserController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/user/account/utils/checkuser/")
    public Map<String,Boolean> getToken(@RequestParam Map<String,String> map){
        Map<String, Boolean> res = new HashMap<>();
        Set<String> s = map.keySet();
        List<String> ls = Arrays.asList(s.toArray(new String[0]));
        String name = ls.get(0);
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
