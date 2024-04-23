package com.example.ivcdancer.service.user.account;

import java.util.Map;

public interface LoginService {

    public Map<String, String> getToken(String name, String password);
}
