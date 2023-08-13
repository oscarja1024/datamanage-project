package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.domain.entity.DeckEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.domain.repository.UserRepository;
import com.oscarjimenez.datamanageproject.service.DTO.DeleteUserDTO;
import com.oscarjimenez.datamanageproject.service.DTO.UserDataDTO;
import com.oscarjimenez.datamanageproject.service.UserDataService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Service
public class UserDataServiceImpl implements UserDataService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity userRegistration(UserDataDTO userData) {

        return userRepository.saveAndFlush(UserEntity.builder()
                .email(userData.getEmail())
                .name(userData.getName())
                .passwd(Base64.getEncoder().encodeToString(userData.getPasswd().getBytes(StandardCharsets.UTF_8))).build());

    }

    @Override
    public UserEntity getUserData(UserDataDTO userDataDTO) {

        var user = userRepository.findById(userDataDTO.getUserId());

        return user.orElseGet(() -> UserEntity.builder().build());

    }

    @Override
    public void deleteUser(UUID userId){

        userRepository.deleteById(userId);

    }
}
