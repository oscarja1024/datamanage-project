package com.oscarjimenez.datamanageproject.service.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDataDTO {

    private UUID userId;
    private String name;
    private String email;
    private String passwd;
}
