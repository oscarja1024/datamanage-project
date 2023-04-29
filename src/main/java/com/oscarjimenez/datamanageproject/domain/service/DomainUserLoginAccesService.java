package com.oscarjimenez.datamanageproject.domain.service;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.UserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.DeletedCount;

public interface DomainUserLoginAccesService {

    UserDataRequest.Document userDataForLogIn(String email);

    public DeletedCount deleteUser(UserDataRequest userDataRequest);

}
