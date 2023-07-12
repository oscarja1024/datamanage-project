package com.oscarjimenez.datamanageproject.domain.client;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.*;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.DeletedCount;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UpdateResponse;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name="feignMongodbConnection", url="${spring.data.mongodb.data-api.base-url}")
public interface FeignMongodbConnection {


    @PostMapping(path="/insertOne")
    InsertedId insertUserData(@RequestHeader("apiKey")String apiKey, @RequestBody UserDataRequest userDataRequest);

    @PostMapping(path="/insertOne")
    InsertedId insertGameUserData(@RequestHeader("apiKey")String apiKey, @RequestBody GameUserDataRequest gameUserDataRequest);

    @PostMapping(path="/insertMany")
    InsertedId insertGameUserData(@RequestHeader("apiKey")String apiKey, @RequestBody List<GameUserDataRequest> gameUserDataRequest);
    @PostMapping(path="/deleteOne")
    DeletedCount deleteUserData(@RequestHeader("apiKey")String apiKey, @RequestBody DeleteUserDataRequest DeleteuserDataRequest);

    @PostMapping(path="/deleteOne")
    DeletedCount deleteGameUserData(@RequestHeader("apiKey")String apiKey, @RequestBody DeleteUserGameDataRequest DeleteuserDataRequest);

    @PostMapping(path="/deleteMany")
    DeletedCount deleteAllGameUserData(@RequestHeader("apiKey")String apiKey, @RequestBody List<DeleteUserGameDataRequest> DeleteuserDataRequest);

    @PostMapping(path="/updateOne")
    UpdateResponse updateUserData(@RequestHeader("apiKey")String apiKey, @RequestBody UserDataRequest userDataRequest);

    @PostMapping(path="/updateOne")
    UpdateResponse updateUserGameData(@RequestHeader("apiKey")String apiKey, @RequestBody GameUserDataRequest gameUserDataRequest);

    @PostMapping(path="/findOne")
    UserDataRequest.Document findUserData(@RequestHeader("apiKey")String apiKey, @RequestBody FindUserDataRequest findUserDataRequest);

    @PostMapping(path="/find")
    UserGameDataResponse findUserGameData(@RequestHeader("apiKey")String apiKey, @RequestBody FindGameUserDataRequest findGameUserDataRequest);
    @PostMapping(path="/findOne")
    UserGameDataResponse findUserGameDataOne(@RequestHeader("apiKey")String apiKey, @RequestBody FindGameUserDataRequest findUserGameDataRequest);
}