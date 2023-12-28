package com.example.SharpCut.SpringRestControllers;

import com.example.SharpCut.Entities.Service;
import com.example.SharpCut.Models.Request.ServiceRequest;
import com.example.SharpCut.Services.All_Services.ImageService;
import com.example.SharpCut.Services.All_Services.ServiceService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ImageService imageService;

    @PostMapping("/addService")
    public ResponseEntity<Map> addService(@RequestBody ServiceRequest serviceRequest) {
        this.serviceService.insertService(serviceRequest);
        Map response = new HashMap();
        response.put("message","Service added successfully!");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/uploadServiceImage/{serviceId}")
    public ResponseEntity<Object> uploadServiceImage(@RequestParam("image") MultipartFile image,@PathVariable Integer serviceId) throws Exception {
        System.out.println(serviceId);

        Map map = this.imageService.addServiceImage(image, serviceId);
        Map response = new HashMap<>();
        response.put("imageUrl",map.get("url"));
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/getAllService")
    public List<Service> getAllService() {
        return this.serviceService.getAll();
    }

}
