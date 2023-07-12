package com.oscarjimenez.datamanageproject.domain.DTOrequest;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DeleteUserDataRequest {

    String dataSource;
    String dataBase;
    String collection;

    Filter filter;

    @Data
    @Builder
    public class Filter{
        UUID userId;

    }
}
