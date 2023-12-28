package com.example.SharpCut.Services.All_Implementations;

import com.example.SharpCut.Entities.Profile;
import com.example.SharpCut.Exceptions.CustomExceptions.CustomGenericException;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Models.Request.UpdateProfile;
import com.example.SharpCut.Repositories.ProfileRepository;
import com.example.SharpCut.Services.All_Services.ProfileService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Profile insertProfile(Profile profile) {
        return this.profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(Integer profileId, Profile profile) throws ResourceNotFoundException {
        Profile profile1 = this.profileRepository.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("profile "+profileId,"does not exist!"));
        profile1.setFirstName(profile.getFirstName());
        profile1.setLastName(profile.getLastName());
        profile1.setPhoneNumber(profile.getPhoneNumber());
        profile1.setAddress(profile.getAddress());
        profile1.setAge(profile.getAge());
        return this.profileRepository.save(profile1);
    }

    @Override
    public Boolean deleteProfile(Integer profileId) throws ResourceNotFoundException {
        Profile profile1 = this.profileRepository.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("profile "+profileId,"does not exist!"));
        this.profileRepository.deleteById(profileId);
        return true;
    }

    @Override
    public List<Profile> getAll() {
        return this.profileRepository.findAll();
    }

    @Override
    public Profile getOneById(Integer profileId) throws ResourceNotFoundException {
        return this.profileRepository.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("profile "+profileId,"does not exist!"));
    }

    @Override
    public Profile updateProfile(Integer profileId, UpdateProfile updateProfile) throws ResourceNotFoundException {
        Profile profile = this.getOneById(profileId);
        profile.setFirstName(updateProfile.getFirstName());
        profile.setLastName(updateProfile.getLastName());
        profile.setAge(updateProfile.getAge());
        profile.setAddress(updateProfile.getAddress());
        profile.setPhoneNumber(updateProfile.getPhoneNumber());
        return this.profileRepository.save(profile);
    }

    @Override
    public Profile resetPassword(Integer profileId, String currentPassword, String newPassword) throws ResourceNotFoundException, CustomGenericException {
        Profile profile = this.getOneById(profileId);
        if (!this.bCryptPasswordEncoder.matches(currentPassword, profile.getPassword())) {
            throw new CustomGenericException("Current Password"," is not correct!");
        }
        profile.setPassword(newPassword);
        return this.profileRepository.save(profile);
    }

    @Override
    public Profile getOneByUsername(String username) throws ResourceNotFoundException {
        Profile profile = this.profileRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User"," does not exist!"));
        return profile;
    }

    @Override
    public String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }
    @Override
    public String resetPassword(String username) throws ResourceNotFoundException {
        Profile profile = this.getOneByUsername(username);
        String password = generateCommonLangPassword();
        profile.setPassword(password);
        this.profileRepository.save(profile);
        return password;
    }
}
