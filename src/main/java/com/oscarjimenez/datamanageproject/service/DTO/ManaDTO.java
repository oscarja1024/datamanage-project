package com.oscarjimenez.datamanageproject.service.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class ManaDTO {

    private String manaCost;
}
