package com.hoaxify.ws.auth;


import com.fasterxml.jackson.annotation.JsonView;
import com.hoaxify.ws.configuration.HoaxifyUserDetails;
import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.shared.Views;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {


    private UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @PostMapping("/api/v1/auth")
    @JsonView(Views.Base.class)
    ResponseEntity<?> handleAuthentication(){

      HoaxifyUserDetails userDetails = (HoaxifyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username= userDetails.getUsername();

        User inDB= userRepository.findByUsername(username);
        if(inDB==null){
            ApiError error = new ApiError(401,"Unauthorized request","/api/v1/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

          // username,displayName ,image
        Map<String ,String> responseBody = new HashMap<>();
        responseBody.put("username",inDB.getUsername());
        responseBody.put("displayName", inDB.getDisplayName());
        responseBody.put("image",inDB.getImage());
            return ResponseEntity.ok(inDB);
    }
}
