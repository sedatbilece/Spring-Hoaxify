package com.hoaxify.ws.hoax;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class HoaxController {

    HoaxService hoaxService;

    public HoaxController(HoaxService hoaxService) {
        this.hoaxService = hoaxService;
    }

    @PostMapping("/hoaxes")
    ResponseEntity<?> saveHoax(@RequestBody Hoax hoax){
        return  hoaxService.save(hoax);
    }

    @GetMapping("/hoaxes")
    Page<Hoax> getHoaxes(@PageableDefault(sort = "id",direction = Sort.Direction.DESC) Pageable page){
        return hoaxService.getHoaxes(page);
    }


}
