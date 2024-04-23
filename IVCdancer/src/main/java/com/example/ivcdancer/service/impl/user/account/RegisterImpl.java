package com.example.ivcdancer.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ivcdancer.mapper.UserMapper;
import com.example.ivcdancer.pojo.User;
import com.example.ivcdancer.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author:RammusLeo
 * @date: 2022, 07 28, 11:55
 */

@Service
public class RegisterImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Map<String, String> register(String name, String password, String confirmedPassword) {
        Map<String,String> map = new HashMap<>();
        if (name == null){
            map.put("error_message","用户名不能为空");
            return map;
        }
        if ( password == null || confirmedPassword == null){
            map.put("error_message","密码不能为空");
            return map;
        }

        name = name.trim();
        if (name.length() == 0){
            map.put("error_message","用户名不能为空");
            return map;
        }

        if (name.length() > 100) {
            map.put("error_message", "用户名长度不能大于100");
            return map;
        }

        if (password.length() > 100 || confirmedPassword.length() > 100) {
            map.put("error_message", "密码长度不能大于100");
            return map;
        }

        if (!password.equals(confirmedPassword)) {
            map.put("error_message", "两次输入的密码不一致");
            return map;
        }
        System.out.println('0');
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            map.put("error_message", "用户名已存在");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);
        String photo = "https://p.qqan.com/up/2021-2/16137992359659254.jpg";
        String email = "202011998079@mail.bnu.edu.cn";
        User user = new User(null, name, encodedPassword, photo, email);
        userMapper.insert(user);
        map.put("error_message", "success");
        return map;


    }

}
