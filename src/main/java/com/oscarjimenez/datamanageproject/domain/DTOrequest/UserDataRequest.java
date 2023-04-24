package com.oscarjimenez.datamanageproject.domain.DTOrequest;

import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@Data
@Builder
public class UserDataRequest {

    String dataSource;
    String dataBase;
    String collection;

    Document document;

    @Data
    @Builder
    public class Document{
        String id;
        String nickName;
        String name;
        String surname;
        String gender;
        String age;
        String email;
        String basicAuth;
    }


}
