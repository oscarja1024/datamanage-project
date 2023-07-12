package com.oscarjimenez.datamanageproject.domain.service.impl;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.DeleteUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.FindUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.UserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.DeletedCount;
import com.oscarjimenez.datamanageproject.domain.client.FeignMongodbConnection;
import com.oscarjimenez.datamanageproject.domain.service.DomainUserLoginAccesService;
import com.oscarjimenez.datamanageproject.domain.utils.utilityDomainClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomainUserLoginAccesServiceImpl implements DomainUserLoginAccesService {
    @Override
    public UserDataRequest.Document userDataForLogIn(String email) {
        return null;
    }

    @Override
    public DeletedCount deleteUser(UserDataRequest userDataRequest) {
        return null;
    }
    /*
    @Autowired
    FeignMongodbConnection feignMongodbConnection;

    @Override
    public UserDataRequest.Document userDataForLogIn(String email) {
        UserDataRequest.Document user = feignMongodbConnection.findUserData(utilityDomainClass.getApiKey()
                , FindUserDataRequest.builder().filter(FindUserDataRequest.Filter.builder().email(email).build()).build());
        return user;
    }

    public DeletedCount deleteUser(UserDataRequest userDataRequest){
        return feignMongodbConnection.deleteUserData(utilityDomainClass.getApiKey(),
                DeleteUserDataRequest.builder().filter(DeleteUserDataRequest.Filter.builder().userId(userDataRequest.getDocument().getId()).build()).build());

    }*/
}
