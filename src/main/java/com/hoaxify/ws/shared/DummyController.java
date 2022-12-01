package com.hoaxify.ws.shared;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @GetMapping("/api/v1/secured")
    String securedPath(){
        return "You are in secure page";
    }
}
