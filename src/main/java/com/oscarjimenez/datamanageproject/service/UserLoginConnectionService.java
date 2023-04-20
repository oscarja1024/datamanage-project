package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.service.DTO.DeleteUserDTO;
import com.oscarjimenez.datamanageproject.service.DTO.UserDataDTO;

public interface UserLoginConnectionService {

    void userAutentication(String email, String passwd);

    void deleteUser(String confirmation, DeleteUserDTO deleteUserDTO);
}
