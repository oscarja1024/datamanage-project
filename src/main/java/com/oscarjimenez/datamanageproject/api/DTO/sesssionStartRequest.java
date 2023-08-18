package com.oscarjimenez.datamanageproject.api.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class sesssionStartRequest {

    private String email;

    private String password;
}
