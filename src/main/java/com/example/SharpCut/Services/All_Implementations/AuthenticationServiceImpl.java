package com.example.SharpCut.Services.All_Implementations;

import com.example.SharpCut.Entities.Enums.Role;
import com.example.SharpCut.Entities.Profile;
import com.example.SharpCut.Repositories.ProfileRepository;
import com.example.SharpCut.Services.All_Services.AuthenticationService;
import com.example.SharpCut.Services.All_Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public Map doLogin(String username, String password) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Profile profile = this.profileRepository.findByUsername(username).get();
        String token = this.jwtService.generateToken(profile);
        Map response = new HashMap<>();
        response.put("token",token);
        return response;
    }

    @Override
    public Map doRegister(String email,String password) {
        Profile profile = new Profile();
        profile.setUsername(email);
        profile.setPassword(password);
        profile.setRole(Role.USER);
        this.profileRepository.save(profile);
        Map response = new HashMap<>();
        response.put("message","user registered successfully");
        return response;
    }
}
