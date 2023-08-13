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



    @PostMapping("/filter")
    public ResponseEntity<FilteredMetadataResponseDTO> filterMetadata(@RequestBody FilterDTO filters) {
        FilteredMetadataResponseDTO filteredMetadata = metadataService.filterMetadata(filters);
        return ResponseEntity.ok(filteredMetadata);
    }

    @GetMapping("/report/{gameId}/{userId}")
    public ResponseEntity<GameEntity> getGameReport(@PathVariable("gameId") UUID gameId,
                                                    @RequestBody UserEntity userId) {
        return ResponseEntity.ok(gameDataService.getGameReportByGameIdAndUserId(gameId,userId));
    }

    @PostMapping("/report")
    public ResponseEntity<GameEntity> saveGameReport(@RequestBody ResultGameDTO gameReport,
                                                     @RequestParam("userId") UUID userId) {
        return ResponseEntity.ok(gameDataService.saveGameReport(gameReport,userId));
    }

    @GetMapping("/compare/{cardId1}/{cardId2}")
    public ResponseEntity<ResultCardDTO> resultCardVsCard(@PathVariable("cardId1") String cardId1,
                                                          @PathVariable("cardId2") String cardId2) {
        ResultCardDTO result = cardService.resultCardVsCard(cardId1, cardId2);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/favorites")
    public ResponseEntity<String> saveFavoriteCards(@RequestParam("cardID") String cardID,
                                                        @RequestParam("userId") UUID userId) {
        try {
            var response =  cardService.saveFavoriteCards(cardID, userId);
            if (!response.getIdorSlug().isEmpty()) {
                return ResponseEntity.ok("Favorite card saved successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save favorite card.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An internal server error occurred: " + e.getMessage());
        }
    }


    @PostMapping("/insert")
    public ResponseEntity<FavDeckEntity> insertOwnedDeck(@RequestBody InsertDeckRequest request) {

        return new ResponseEntity<>(deckDataService.saveOwnedDeck(request,request.getUserId()), HttpStatus.CREATED);
    }

    @GetMapping("/get/{deckId}/{userId}")
    public ResponseEntity<FavDeckEntity> getOwnedDeck(
            @PathVariable UUID deckId,
            @RequestBody UserEntity userId
    ) {
        return new ResponseEntity<>( deckDataService.findByUserIdandDeckId(deckId,userId),HttpStatus.OK);
    }

    @PostMapping("/insertReport")
    public ResponseEntity<DeckEntity> insertDeckReport(@PathVariable UUID deckId,
                                                   @RequestBody UserEntity userId) {
        return new ResponseEntity<>(deckDataService.generateDeckResport(userId,deckId), HttpStatus.CREATED);
    }

    @GetMapping("/getReport/{deckReportId}")
    public ResponseEntity<DeckEntity> getDeckDataReport(
            @PathVariable UUID deckReportId
    ) {
        return new ResponseEntity<>(deckDataService.getDeckReport(deckReportId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteFavCatd/{cardId}/{userId}")
    public ResponseEntity<String> deleteFavCard(@PathVariable String cardId, @PathVariable UUID userId){
        cardService.deleteFavoriteCards(cardId, userId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("/deleteGameReport/{gameId}/{userId}")
    public ResponseEntity<String> deleteGameReport(@PathVariable UUID gameId){
        gameDataService.deleteGameReport(gameId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("/deleteOwnedDeck/{deckId}/{userId}")
    public ResponseEntity<String> deleteOwnedDeck(@PathVariable UUID deckId){
        deckDataService.deleteOwnedDeck(deckId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("/deleteDeckReport/{deckId}/{deckReportId}/{userId}")
    public ResponseEntity<String> deleteDeckReport(@PathVariable  UUID deckReportId){
        deckDataService.deleteDeckReport(deckReportId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}

