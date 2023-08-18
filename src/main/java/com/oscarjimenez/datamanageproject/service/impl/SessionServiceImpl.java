package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.api.DTO.response.sessionResponse;
import com.oscarjimenez.datamanageproject.domain.entity.SessionEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.domain.repository.SessionRepository;
import com.oscarjimenez.datamanageproject.domain.repository.UserRepository;
import com.oscarjimenez.datamanageproject.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public sessionResponse getSession(String email, String password) {

        var user = userRepository.findByEmailAndPasswd(email,password);

        if(user != null){

            var sessionEntity = SessionEntity.builder().user(user).build();

            var session = sessionRepository.saveAndFlush(sessionEntity);

            return sessionResponse.builder().sessionId(session.getSessionId()).userId(user.getUserId()).build();
        }

        return sessionResponse.builder().build();
    }

    @Override
    public void deleteSession(UUID sessionId) {

        sessionRepository.deleteById(sessionId);

    }

    @Override
    public boolean sessionVerify(UUID sessionId, UserEntity userId) {

        var session = sessionRepository.findByUserAndSessionId(userId,sessionId);

        if(session != null){
            return true;
        }

        return false;
    }
}
