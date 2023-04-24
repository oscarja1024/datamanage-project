package com.oscarjimenez.datamanageproject.domain.utils;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

@UtilityClass
public class utilityDomainClass {

    @Value("${spring.data.mongodb.data-api.api-key}")
    private String apiKey;

    public String getApiKey(){
        return apiKey;
    }
}
