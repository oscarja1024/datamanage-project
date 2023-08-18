package com.oscarjimenez.datamanageproject.api.controller;

import com.oscarjimenez.datamanageproject.api.DTO.InsertDeckRequest;
import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.domain.entity.*;
import com.oscarjimenez.datamanageproject.service.*;
import com.oscarjimenez.datamanageproject.service.DTO.ResultCardDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ResultGameDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oscarjimenez.datamanageproject.service.DTO.FilteredMetadataResponseDTO;
import com.oscarjimenez.datamanageproject.service.DTO.FilterDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/userData")
public class UserDataAccesController {

    @Autowired
    private CardDataClasifierService cardService;

    @Autowired
    private DeckFinderDataService deckService;

    @Autowired
    private DeckUserDataService deckDataService;

    @Autowired
    private GameDataUserService gameDataService;

    @Autowired
    private MetadataClasifierService metadataService;


    @Autowired
    private SessionService sessionService;

    @PostMapping("/filter")
    public ResponseEntity<FilteredMetadataResponseDTO> filterMetadata(@RequestBody FilterDTO filters) {

        FilteredMetadataResponseDTO filteredMetadata = metadataService.filterMetadata(filters);
        return ResponseEntity.ok(filteredMetadata);
    }

    @GetMapping("/report/{gameId}")
    public ResponseEntity<GameEntity> getGameReport(@PathVariable("gameId") UUID gameId,
                                                    @RequestBody UserEntity userId,
                                                    @RequestHeader("sessionId") UUID sessionId) {

        if(sessionService.sessionVerify(sessionId,userId)){
            return ResponseEntity.ok(gameDataService.getGameReportByGameIdAndUserId(gameId,userId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/report/{sessionId}")
    public ResponseEntity<GameEntity> saveGameReport(@RequestBody ResultGameDTO gameReport,
                                                     @RequestParam("userId") UUID userId,
                                                     @RequestHeader("sessionId") UUID sessionId) {

        if(sessionService.sessionVerify(sessionId,UserEntity.builder().userId(userId).build())){
            return ResponseEntity.ok(gameDataService.saveGameReport(gameReport,userId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @GetMapping("/compare/{cardId1}/{cardId2}")
    public ResponseEntity<ResultCardDTO> resultCardVsCard(@PathVariable("cardId1") String cardId1,
                                                          @PathVariable("cardId2") String cardId2) {
        ResultCardDTO result = cardService.resultCardVsCard(cardId1, cardId2);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/favorites")
    public ResponseEntity<String> saveFavoriteCards(@RequestParam("cardID") String cardID,
                                                    @RequestParam("userId") UUID userId,
                                                    @RequestHeader("sessionId") UUID sessionId) {
        try {

            if(sessionService.sessionVerify(sessionId,UserEntity.builder().userId(userId).build())){
                var response =  cardService.saveFavoriteCards(cardID, userId);
                if (!response.getIdorSlug().isEmpty()) {
                    return ResponseEntity.ok("Favorite card saved successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save favorite card.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An internal server error occurred: " + e.getMessage());
        }
    }


    @PostMapping("/insert")
    public ResponseEntity<FavDeckEntity> insertOwnedDeck(@RequestBody InsertDeckRequest request,
                                                         @RequestHeader("sessionId") UUID sessionId) {
        if(sessionService.sessionVerify(sessionId,UserEntity.builder().userId(request.getUserId()).build())){
            return new ResponseEntity<>(deckDataService.saveOwnedDeck(request,request.getUserId()), HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/get/{deckId}/{userId}")
    public ResponseEntity<FavDeckEntity> getOwnedDeck(
            @PathVariable UUID deckId,
            @RequestBody UserEntity userId,
            @RequestHeader("sessionId") UUID sessionId
    ) {
        if(sessionService.sessionVerify(sessionId,userId)){
            return new ResponseEntity<>( deckDataService.findByUserIdandDeckId(deckId,userId),HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/insertReport/{deckId}/{userId}")
    public ResponseEntity<DeckEntity> insertDeckReport(@PathVariable UUID deckId,
                                                   @PathVariable UUID userId,
                                                       @RequestHeader("sessionId") UUID sessionId) {
        if(sessionService.sessionVerify(sessionId,UserEntity.builder().userId(userId).build())){
            return new ResponseEntity<>(deckDataService.generateDeckResport(UserEntity.builder().userId(userId).build(),deckId), HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/getReport/{deckReportId}")
    public ResponseEntity<DeckEntity> getDeckDataReport(
            @PathVariable UUID deckReportId,
            @RequestBody UserEntity userId,
            @RequestHeader("sessionId") UUID sessionId
    ) {
        if(sessionService.sessionVerify(sessionId,userId)){
            return new ResponseEntity<>(deckDataService.getDeckReport(deckReportId), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/deleteFavCatd/{cardId}/{userId}")
    public ResponseEntity<String> deleteFavCard(@PathVariable String cardId, @PathVariable UUID userId,
                                                @RequestHeader("sessionId") UUID sessionId){
        if(sessionService.sessionVerify(sessionId,UserEntity.builder().userId(userId).build())){
            cardService.deleteFavoriteCards(cardId, userId);
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/deleteGameReport/{gameId}/{userId}")
    public ResponseEntity<String> deleteGameReport(@PathVariable UUID gameId,
                                                   @PathVariable UUID userId,
                                                   @RequestHeader("sessionId") UUID sessionId){
        if(sessionService.sessionVerify(sessionId,UserEntity.builder().userId(userId).build())){
            gameDataService.deleteGameReport(gameId);
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @DeleteMapping("/deleteOwnedDeck/{deckId}/{userId}")
    public ResponseEntity<String> deleteOwnedDeck(@PathVariable UUID deckId,
                                                  @PathVariable UUID userId,
                                                  @RequestHeader("sessionId") UUID sessionId){
        if(sessionService.sessionVerify(sessionId,UserEntity.builder().userId(userId).build())){
            deckDataService.deleteOwnedDeck(deckId);
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @DeleteMapping("/deleteDeckReport/{deckId}/{deckReportId}/{userId}")
    public ResponseEntity<String> deleteDeckReport(@PathVariable  UUID deckReportId,
                                                   @PathVariable UUID userId,
                                                   @RequestHeader("sessionId") UUID sessionId){
        if(sessionService.sessionVerify(sessionId,UserEntity.builder().userId(userId).build())){
            deckDataService.deleteDeckReport(deckReportId);
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @GetMapping("/game-reports")
    public ResponseEntity<List<GameEntity>> findGameReportsByUserId(@RequestBody UserEntity userId,
                                                                    @RequestHeader("sessionId") UUID sessionId) {
        if(sessionService.sessionVerify(sessionId,userId)){
            return ResponseEntity.ok(gameDataService.findGameReportsByUserId(userId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/ownedDecksByUser")
    public ResponseEntity<List<FavDeckEntity>> findDecksByUserId(@RequestBody UserEntity userId,
                                                                 @RequestHeader("sessionId") UUID sessionId) {
        if(sessionService.sessionVerify(sessionId,userId)){
            return ResponseEntity.ok(deckDataService.findDecksByUserId(userId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/deck-reports")
    public ResponseEntity<List<DeckEntity>> findDeckReportsByUserId(@RequestBody UserEntity userId,
                                                                    @RequestHeader("sessionId") UUID sessionId) {
        if(sessionService.sessionVerify(sessionId,userId)){
            return ResponseEntity.ok(deckDataService.findDeckReportsByUserId(userId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/favorite-cards")
    public ResponseEntity<List<CardEntity>> getFavoriteCardsByUser(@RequestBody UserEntity userId,
                                                                   @RequestHeader("sessionId") UUID sessionId) {
        if(sessionService.sessionVerify(sessionId,userId)){
            return ResponseEntity.ok(cardService.getFavoriteCardsByUser(userId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}

