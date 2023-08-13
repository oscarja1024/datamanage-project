package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.service.DTO.DeleteUserDTO;
import com.oscarjimenez.datamanageproject.service.DTO.UserDataDTO;

import java.util.UUID;

public interface UserDataService {


    UserEntity userRegistration(UserDataDTO userData);

    UserEntity getUserData(UserDataDTO userDataDTO);

    void deleteUser(UUID userId);
}
