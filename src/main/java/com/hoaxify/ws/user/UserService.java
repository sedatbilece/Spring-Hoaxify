package com.hoaxify.ws.user;


import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.shared.GenericResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseEntity<?> save(User user) {

        ApiError error = new ApiError(400,"validation error","/api/v1/users");
        Map<String,String> validationerror = new HashMap<>();
        if (user.getUsername()==null || user.getUsername().isEmpty()){
            validationerror.put("username","User Name cannot be empty");
        }
        else{
              User usname = userRepository.findByUsername(user.getUsername());
              if(usname!=null){
                  validationerror.put("username","User Name  must be unique");
              }
            if(user.getUsername().length()<4 || user.getUsername().length()>50){
                validationerror.put("username","User Name  must be 4-50 Character"); }
        }
        if (user.getDisplayName()==null || user.getDisplayName().isEmpty()){
            validationerror.put("displayName","Display Name cannot be empty");
        }
        else{
            if(user.getDisplayName().length()<4 || user.getDisplayName().length()>50){
                validationerror.put("displayName","Display Name  must be 4-50 Character"); }
        }
        if (user.getPassword()==null || user.getPassword().isEmpty()){
            validationerror.put("password","Password cannot be empty");
        }
        else{
            if(user.getPassword().length()<8 || user.getPassword().length()>20){
                validationerror.put("password","Password  must be 8-20 Character"); }
            if(! user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")){
                validationerror.put("password","Password must have big char,small char and digit ");
            }

        }
        if(validationerror.size()>0){
            error.setValidationErrors(validationerror);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return  ResponseEntity.ok(new GenericResponse("user created"));
    }

    public PageImpl<UserResponseDto> getAllUsers(int currentPage, int pageSize) {
        
      Pageable page= PageRequest.of(currentPage,pageSize);
       Page<User> userPage= userRepository.findAll(page);//get page type all users
       List<User> userList= userPage.getContent();//get user list from pages

       List<UserResponseDto> responseList=  userList.stream().map(user -> new UserResponseDto(//mapping dto
               user.getId(),
               user.getUsername(),
               user.getDisplayName(),
               user.getImage() )).collect(Collectors.toList());

        return new PageImpl<UserResponseDto>(responseList, page, userPage.getTotalElements());//convert Page list
    }

    public ResponseEntity<?> getUser(String username) {

        User user= userRepository.findByUsername(username);

        if(user==null){
            ApiError error = new ApiError(404,"User not Found","/api/v1/users/"+username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        UserResponseDto getuser= new UserResponseDto(//mapping dto
                user.getId(),
                user.getUsername(),
                user.getDisplayName(),
                user.getImage() );

        return   ResponseEntity.ok(getuser);
    }

    public ResponseEntity<?> updateUser(String username, UserUpdateDto userUpdateDto) {

        if(userUpdateDto.getDisplayName().length()<4 ||userUpdateDto.getDisplayName().length()>255 ){
            ApiError error = new ApiError(400,"Not valid request","/api/v1/users/"+username);
            Map<String,String> validationerror = new HashMap<>();
            validationerror.put("displayName","Display Name  must be 4-50 Character");
            error.setValidationErrors(validationerror);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
            User inDB = userRepository.findByUsername(username);
            inDB.setDisplayName(userUpdateDto.getDisplayName());

        if (userUpdateDto.getImage() !=null){
            inDB.setImage(userUpdateDto.getImage());
        }
        userRepository.save(inDB);

        UserResponseDto getUser= new UserResponseDto(//mapping dto
                inDB.getId(),
                inDB.getUsername(),
                inDB.getDisplayName(),
                inDB.getImage() );

        return   ResponseEntity.ok(getUser);
    }
}
