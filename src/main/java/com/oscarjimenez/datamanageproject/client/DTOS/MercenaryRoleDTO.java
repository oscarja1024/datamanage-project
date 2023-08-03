package com.oscarjimenez.datamanageproject.client.DTOS;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class MercenaryRoleDTO {

    private String slug;
    private String id;
    private String name;
}
