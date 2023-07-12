package com.oscarjimenez.datamanageproject.domain.DTOrequest;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FindUserDataRequest {

    String dataSource;
    String dataBase;
    String collection;

    Filter filter;

    @Data
    @Builder
    public static class Filter{
        UUID userId;

        String email;
    }
}
