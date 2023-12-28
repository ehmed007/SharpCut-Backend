package com.example.SharpCut.Services.All_Services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageService {
    public Map addProfileImage(MultipartFile image, Integer profileId) throws Exception;
    public Map addBarberImage(MultipartFile image, Integer barberId) throws Exception;
    public Map addServiceImage(MultipartFile image, Integer serviceId) throws Exception;
}
