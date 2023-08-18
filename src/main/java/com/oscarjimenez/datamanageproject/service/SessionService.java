package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.api.DTO.response.sessionResponse;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;

import java.util.UUID;

public interface SessionService {

    sessionResponse getSession(String email, String password);

    void deleteSession(UUID sessionId);

    boolean sessionVerify(UUID sessionId, UserEntity userId);
}
