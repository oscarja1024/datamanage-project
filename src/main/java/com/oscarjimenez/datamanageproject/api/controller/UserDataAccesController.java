package com.oscarjimenez.datamanageproject.api.controller;

import com.oscarjimenez.datamanageproject.api.DTO.InsertDeckRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.DeletedCount;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;
import com.oscarjimenez.datamanageproject.domain.service.GameUserDeckDataService;
import com.oscarjimenez.datamanageproject.service.*;
import com.oscarjimenez.datamanageproject.service.DTO.ResultCardDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ResultGameDTO;
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
    private GameDataUserService gameDataService;

    @Autowired
    private MetadataClasifierService metadataService;

    @Autowired
    private GameUserDeckDataService gameUserDeckDataService;

    @PostMapping("/filter")
    public ResponseEntity<FilteredMetadataResponseDTO> filterMetadata(@RequestBody FilterDTO filters) {
        FilteredMetadataResponseDTO filteredMetadata = metadataService.filterMetadata(filters);
        return ResponseEntity.ok(filteredMetadata);
    }

    @GetMapping("/report/{gameId}/{userId}")
    public ResponseEntity<ResultGameDTO> getGameReport(@PathVariable("gameId") UUID gameId,
                                                       @PathVariable("userId") UUID userId) {
        ResultGameDTO result = gameDataService.getGameReport(gameId, userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/report")
    public ResponseEntity<InsertedId> saveGameReport(@RequestBody ResultGameDTO gameReport,
                                                     @RequestParam("userId") UUID userId) {
        InsertedId savedId = gameDataService.saveGameReport(gameReport, userId);
        return ResponseEntity.ok(savedId);
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
            boolean saved = cardService.saveFavoriteCards(cardID, userId);
            if (saved) {
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
    public ResponseEntity<InsertedId> insertOwnedDeck(@RequestBody InsertDeckRequest request) {
        InsertedId insertedId = gameUserDeckDataService.insertOwnedDeck(
                request.getCardIds(),
                request.getHeroId(),
                request.getUserId(),
                request.getDeckId()
        );
        return new ResponseEntity<>(insertedId, HttpStatus.CREATED);
    }

    @GetMapping("/get/{deckId}/{userId}")
    public ResponseEntity<UserGameDataResponse> getOwnedDeck(
            @PathVariable UUID deckId,
            @PathVariable UUID userId
    ) {
        UserGameDataResponse response = gameUserDeckDataService.getOwnedDeck(deckId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/insertReport")
    public ResponseEntity<InsertedId> insertDeckReport(@RequestBody GameUserDataRequest request) {
        InsertedId insertedId = gameUserDeckDataService.insertDeckReport(request);
        return new ResponseEntity<>(insertedId, HttpStatus.CREATED);
    }

    @GetMapping("/getReport/{deckId}/{userId}")
    public ResponseEntity<UserGameDataResponse> getDeckDataReport(
            @PathVariable UUID deckId,
            @PathVariable UUID userId
    ) {
        UserGameDataResponse response = gameUserDeckDataService.getDeckDataReport(deckId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteFavCatd/{cardId}/{userId}")
    public ResponseEntity<DeletedCount> deleteFavCard(@PathVariable String cardId, @PathVariable UUID userId){
        var response = cardService.deleteFavoriteCards(cardId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteGameReport/{gameId}/{userId}")
    public ResponseEntity<DeletedCount> deleteGameReport(@PathVariable UUID gameId, @PathVariable UUID userId){
        var response = gameDataService.deleteGameReport(gameId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteOwnedDeck/{deckId}/{userId}")
    public ResponseEntity<DeletedCount> deleteOwnedDeck(@PathVariable UUID deckId, @PathVariable UUID userId){
        var response = gameUserDeckDataService.deleteOwnedDeck(deckId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDeckReport/{deckId}/{deckReportId}/{userId}")
    public ResponseEntity<DeletedCount> deleteDeckReport(@PathVariable UUID deckId, @PathVariable  UUID deckReportId,@PathVariable UUID userId){
        var response = gameUserDeckDataService.deleteDeckDataReport(deckId, deckReportId , userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

