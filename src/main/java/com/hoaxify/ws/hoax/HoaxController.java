package com.hoaxify.ws.hoax;


import com.hoaxify.ws.shared.GenericResponse;
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
    GenericResponse saveHoax(@RequestBody Hoax hoax){

        hoaxService.save(hoax);

        return new GenericResponse("hoax is saved");

    }
}
