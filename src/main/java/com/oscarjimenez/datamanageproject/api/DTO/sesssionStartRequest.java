package com.oscarjimenez.datamanageproject.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class sesssionStartRequest {

    private String email;

    private String password;
}
