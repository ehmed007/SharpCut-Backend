package com.example.SharpCut.Services.All_Implementations;

import com.cloudinary.Cloudinary;
import com.example.SharpCut.Entities.Barber;
import com.example.SharpCut.Entities.Profile;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Repositories.BarberRepository;
import com.example.SharpCut.Repositories.ProfileRepository;
import com.example.SharpCut.Repositories.ServiceRepository;
import com.example.SharpCut.Services.All_Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private BarberRepository barberRepository;

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiSecret;

    @Value("${cloudinary.api-secret}")
    private String apiKey;


    @Override
    public Map addProfileImage(MultipartFile image, Integer profileId) throws Exception {
        Cloudinary cloudinary = new Cloudinary();
        cloudinary.config.cloudName = cloudName;
        cloudinary.config.apiSecret = apiSecret;
        cloudinary.config.apiKey = apiKey;
        cloudinary.api().createFolder("SharpCut/Profile_SharpCut",Map.of());
        Map options = new HashMap<>();
        options.put("folder","SharpCut/Profile_SharpCut");

        Profile profile = this.profileRepository.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("Profile "+profileId,"does not exist!"));
        if (profile.getImgUrl() != null) {
            cloudinary.uploader().destroy(profile.getImagePublicId(),Map.of());
        }
        Map<?,?> response = cloudinary.uploader().upload(image.getBytes(), options);
        System.out.println(response);
        profile.setImgUrl((String) response.get("url"));
        profile.setImagePublicId((String) response.get("public_id"));
        this.profileRepository.save(profile);
        return response;
    }

    @Override
    public Map addBarberImage(MultipartFile image, Integer barberId) throws Exception {
        Cloudinary cloudinary = new Cloudinary();
        cloudinary.config.cloudName = cloudName;
        cloudinary.config.apiSecret = apiSecret;
        cloudinary.config.apiKey = apiKey;
        cloudinary.api().createFolder("SharpCut/Barber_SharpCut",Map.of());
        Map options = new HashMap<>();
        options.put("folder","SharpCut/Barber_SharpCut");

        Barber barber = this.barberRepository.findById(barberId).orElseThrow(() -> new ResourceNotFoundException("Barber "+barberId, "does not exist!" ));
        if (barber.getImgUrl() != null) {
            cloudinary.uploader().destroy(barber.getImgPublicId(),Map.of());
        }
        Map<?,?> response = cloudinary.uploader().upload(image.getBytes(), options);
        barber.setImgUrl((String) response.get("url"));
        barber.setImgPublicId((String) response.get("public_id"));
        this.barberRepository.save(barber);
        return response;
    }

    @Override
    public Map addServiceImage(MultipartFile image, Integer serviceId) throws Exception {
        Cloudinary cloudinary = new Cloudinary();
        cloudinary.config.cloudName = cloudName;
        cloudinary.config.apiSecret = apiSecret;
        cloudinary.config.apiKey = apiKey;
        cloudinary.api().createFolder("SharpCut/Service_SharpCut",Map.of());
        Map options = new HashMap<>();
        options.put("folder","SharpCut/Service_SharpCut");

        com.example.SharpCut.Entities.Service service1 = this.serviceRepository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("service "+serviceId,"does not exist!"));
        if (service1.getImgUrl() != null) {
            cloudinary.uploader().destroy(service1.getImgPublicId(),Map.of());
        }
        Map<?,?> response = cloudinary.uploader().upload(image.getBytes(), options);
        service1.setImgUrl((String) response.get("url"));
        service1.setImgPublicId((String) response.get("public_id"));
        this.serviceRepository.save(service1);
        return response;
    }
}
