package com.example.ivcdancer.controller.user.account;

import com.example.ivcdancer.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description:
 * @author:RammusLeo
 * @date: 2022, 07 28, 11:30
 */
@RestController
public class InfoController{
    @Autowired
    private InfoService infoService;

    @GetMapping("/user/account/info/")
    public Map<String, String> getInfo() {
        return infoService.getInfo();
    }

}
