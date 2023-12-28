package com.example.SharpCut.SpringRestControllers;

import com.example.SharpCut.Entities.Barber;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.SharpCut.Models.Request.BarberRequest;
import com.example.SharpCut.Models.Request.RequestRateBarber;
import com.example.SharpCut.Models.Response.BarberResponse;
import com.example.SharpCut.Services.All_Services.BarberService;
import com.example.SharpCut.Services.All_Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/barber")
public class BarberController {

    @Autowired
    private BarberService barberService;

    @Autowired
    private ImageService imageService;

    @PostMapping("/addBarber")
    public ResponseEntity<Map> addBarber(@RequestBody BarberRequest barberRequest) {
        this.barberService.insertBarber(barberRequest);
        Map response = new HashMap<>();
        response.put("message","barber added successfully!");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/getAllBarber")
    public ResponseEntity<List> getAllBarber() {
        List<BarberResponse> barberResponseList = this.barberService.getAll_Response();
        return new ResponseEntity<>(barberResponseList, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/getOneBarber/{barberId}")
    public ResponseEntity<BarberResponse> getOneBarber(@PathVariable Integer barberId) throws ResourceNotFoundException {
        BarberResponse barberResponse = this.barberService.getOneById_Response(barberId);
        return new ResponseEntity<>(barberResponse, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/uploadBarberImage/{barberId}")
    public ResponseEntity<Map> uploadBarberImage(@RequestParam("image") MultipartFile image,@PathVariable Integer barberId) throws Exception {
        Map map = this.imageService.addBarberImage(image, barberId);
        Map response = new HashMap<>();
        response.put("imageUrl",map.get("url"));
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/rateBarber/{barberId}")
    public ResponseEntity<BarberResponse> rateBarber(@RequestBody RequestRateBarber requestRateBarber, @PathVariable Integer barberId) throws ResourceNotFoundException {
        BarberResponse barberResponse= this.barberService.rateBarber_Response(barberId, requestRateBarber.getRating());
        return new ResponseEntity<>(barberResponse, HttpStatusCode.valueOf(200));
    }


}
