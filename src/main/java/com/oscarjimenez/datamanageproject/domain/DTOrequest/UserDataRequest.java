package com.oscarjimenez.datamanageproject.domain.DTOrequest;

public class UserDataRequest {

    String dataSource;
    String dataBase;
    String collection;

    Document document;

    public class Document{
        String id;
        String nickName;
        String name;
        String surname;
        String gender;
        String age;
        String email;
        String passwd;
    }


}
