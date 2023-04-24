package com.oscarjimenez.datamanageproject.domain.service.impl;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.UserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UpdateResponse;
import com.oscarjimenez.datamanageproject.domain.client.FeignMongodbConnection;
import com.oscarjimenez.datamanageproject.domain.service.DomainAccesUserSignUpService;
import com.oscarjimenez.datamanageproject.domain.utils.utilityDomainClass;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomainServiceUserSignUpAccesImpl implements DomainAccesUserSignUpService {

    @Autowired
    FeignMongodbConnection feignMongodbConnection;

    @Override
    public InsertedId signUpUser(UserDataRequest userDataRequest) {
        InsertedId newUser = feignMongodbConnection.insertUserData(utilityDomainClass.getApiKey(),userDataRequest);
        return newUser;
    }

    public UpdateResponse updateUser(UserDataRequest userDataRequest){
        UpdateResponse updated = feignMongodbConnection.updateUserData(utilityDomainClass.getApiKey(),userDataRequest);
        return updated;
    }


}
