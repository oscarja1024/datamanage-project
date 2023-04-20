package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.service.DTO.UserDataDTO;
import org.apache.catalina.User;

public interface UserDataService {


    void userRegistration(UserDataDTO userData);

    void userDataUpdate(UserDataDTO userDataDTO);
}
