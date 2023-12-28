package com.example.SharpCut.Services.All_Services;

import java.util.Map;

public interface AuthenticationService {
    public Map doLogin(String username, String password);
    public Map doRegister(String email,String password);
}
