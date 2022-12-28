package com.hoaxify.ws.hoax;

import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.shared.GenericResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class HoaxService {

    HoaxRepository hoaxRepository;

    public HoaxService(HoaxRepository hoaxRepository) {
        this.hoaxRepository = hoaxRepository;
    }

    public ResponseEntity<?> save(Hoax hoax) {
        ApiError error = new ApiError(400,"validation error","/api/v1/users");
        Map<String,String> validationerror = new HashMap<>();

        if(hoax.getContent().length()<1 || hoax.getContent().length()> 1000){
            validationerror.put("Hoax","Hoax  must be 1-1000 Character");
        }

        if(validationerror.size()>0){
            error.setValidationErrors(validationerror);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        hoax.setTimestamp(new Date());
        hoaxRepository.save(hoax);
       return ResponseEntity.ok(new GenericResponse("Hoax created"));
    }


    public Page<Hoax> getHoaxes(Pageable page) {
        return hoaxRepository.findAll(page);
    }

    public Page<Hoax> getHoaxesRelative(Pageable page, long id) {
           return hoaxRepository.findIdLessThan(id,page);
    }
}
