package com.example.SharpCut.Services.All_Services;

import com.example.SharpCut.Entities.Profile;
import com.example.SharpCut.Exceptions.CustomExceptions.CustomGenericException;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Models.Request.UpdateProfile;

import java.util.List;

public interface ProfileService {
    public Profile insertProfile(Profile profile);
    public Profile updateProfile(Integer profileId, Profile profile) throws ResourceNotFoundException;
    public Boolean deleteProfile(Integer profileId) throws ResourceNotFoundException;
    public List<Profile> getAll();
    public Profile getOneById(Integer profileId) throws ResourceNotFoundException;
    public Profile updateProfile(Integer profileId, UpdateProfile updateProfile) throws ResourceNotFoundException;
    public Profile resetPassword(Integer profileId, String currentPassword, String newPassword) throws ResourceNotFoundException, CustomGenericException;
    public Profile getOneByUsername(String username) throws ResourceNotFoundException;
    public String resetPassword(String username) throws ResourceNotFoundException;

    public String generateCommonLangPassword();
}
