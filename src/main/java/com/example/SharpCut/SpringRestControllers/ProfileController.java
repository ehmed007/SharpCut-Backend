package com.example.SharpCut.SpringRestControllers;

import com.example.SharpCut.Entities.Profile;
import com.example.SharpCut.Exceptions.CustomExceptions.CustomGenericException;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Models.Request.ResetPassword;
import com.example.SharpCut.Models.Request.UpdateProfile;
import com.example.SharpCut.Services.All_Services.ImageService;
import com.example.SharpCut.Services.All_Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;  
    @Autowired
    private ImageService imageService;

    @PostMapping("/updateProfile")
    public ResponseEntity<Profile> updateProfile(@RequestBody UpdateProfile updateProfile) throws ResourceNotFoundException {
        Profile profile1 = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Profile profile = this.profileService.updateProfile(profile1.getId(), updateProfile);
        return new ResponseEntity<>(profile, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/uploadProfileImage")
    public ResponseEntity<Map> uploadProfileImage(@RequestParam("image") MultipartFile image) throws Exception {
        Profile obj = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map response = new HashMap<>();
        String url = (String) this.imageService.addProfileImage(image,obj.getId()).get("url");
        response.put("imageUrl",url);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/getProfile")
    public ResponseEntity<Profile> getProfile() throws ResourceNotFoundException {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Profile profile1 = this.profileService.getOneById(profile.getId());
        return new ResponseEntity<>(profile1, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Map> resetPassword(@RequestBody ResetPassword resetPassword) throws CustomGenericException, ResourceNotFoundException {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.profileService.resetPassword(profile.getId(), resetPassword.getCurrentPassword(), resetPassword.getNewPassword());
        Map response = new HashMap();
        response.put("message","Password Changed Successfully!");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

}
