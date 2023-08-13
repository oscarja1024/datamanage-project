package com.oscarjimenez.datamanageproject.api.controller;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.service.DTO.UserDataDTO;
import com.oscarjimenez.datamanageproject.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserDataService userDataService;

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

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userDataService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
