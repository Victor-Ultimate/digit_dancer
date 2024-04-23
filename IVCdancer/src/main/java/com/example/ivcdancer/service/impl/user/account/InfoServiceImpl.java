package com.example.ivcdancer.service.impl.user.account;

import com.example.ivcdancer.pojo.User;
import com.example.ivcdancer.service.impl.utils.UserDetailsImpl;
import com.example.ivcdancer.service.user.account.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author:RammusLeo
 * @date: 2022, 07 28, 11:03
 */

@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public Map<String, String> getInfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Map<String,String> map = new HashMap<>();
        map.put("error_message","success");
        map.put("id", user.getId().toString());
        map.put("name",user.getName());
        map.put("photo",user.getPhoto());

        return map;
    }

}
