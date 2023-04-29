package com.oscarjimenez.datamanageproject.domain.service;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.UserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UpdateResponse;

public interface DomainAccesUserSignUpService {

    InsertedId signUpUser(UserDataRequest userDataRequest);

    UpdateResponse updateUser(UserDataRequest userDataRequest);
}
