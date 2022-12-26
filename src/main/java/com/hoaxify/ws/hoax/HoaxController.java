package com.hoaxify.ws.hoax;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HoaxController {

    HoaxService hoaxService;

    public HoaxController(HoaxService hoaxService) {
        this.hoaxService = hoaxService;
    }

    @PostMapping("/api/v1/hoaxes")
    ResponseEntity<?> saveHoax(@RequestBody Hoax hoax){

        return  hoaxService.save(hoax);



    }
}
