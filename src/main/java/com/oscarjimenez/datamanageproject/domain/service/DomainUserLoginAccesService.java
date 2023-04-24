package com.oscarjimenez.datamanageproject.domain.service;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.UserDataRequest;

public interface DomainUserLoginAccesService {

    UserDataRequest.Document userDataForLogIn(String email);


}
