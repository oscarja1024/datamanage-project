package com.oscarjimenez.datamanageproject.api.controller;
import com.oscarjimenez.datamanageproject.api.DTO.response.sessionResponse;
import com.oscarjimenez.datamanageproject.api.DTO.sesssionStartRequest;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.service.DTO.UserDataDTO;
import com.oscarjimenez.datamanageproject.service.SessionService;
import com.oscarjimenez.datamanageproject.service.UserDataService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private SessionService sessionService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserDataDTO userData) {
        UserEntity registeredUser = userDataService.userRegistration(userData);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable UUID userId) {
        UserEntity user = userDataService.getUserData(UserDataDTO.builder().userId(userId).build());
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userDataService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/session")
    public ResponseEntity<sessionResponse> startSession(@RequestBody sesssionStartRequest session){

        return ResponseEntity.ok(sessionService.getSession(session.getEmail(), Base64.encodeBase64String(session.getPassword().getBytes(StandardCharsets.UTF_8))));


    }

    @DeleteMapping("/session/{sessionId}")
    public ResponseEntity<Void> deleteSession(@PathVariable UUID sessionId){
        sessionService.deleteSession(sessionId);
        return ResponseEntity.noContent().build();
    }
}
