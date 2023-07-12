package com.oscarjimenez.datamanageproject.api.controller;

import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.service.GameUserCardDataService;
import com.oscarjimenez.datamanageproject.service.*;
import com.oscarjimenez.datamanageproject.service.DTO.ResultCardDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ResultGameDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.MetadataResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oscarjimenez.datamanageproject.service.DTO.FilteredMetadataResponseDTO;
import com.oscarjimenez.datamanageproject.service.DTO.FilterDTO;

import java.util.List;
import java.util.UUID;

@RestController
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
    private MetadataFinderService metadataFinderService;

    @GetMapping
    public ResponseEntity<MetadataResponseDTO> getAllMetadata() {
        MetadataResponseDTO metadata = metadataFinderService.getAllMetada();
        return ResponseEntity.ok(metadata);
    }

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

    @GetMapping("/by-card-list-and-hero")
    public ResponseEntity<DeckDTO> getDeckByCardListAndHero(@RequestParam("cardIds") List<String> cardIds,
                                                            @RequestParam("heroId") String heroId) {
        DeckDTO result = deckService.getDeckByCardListAndHero(cardIds, heroId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-card-list-auto-hero")
    public ResponseEntity<DeckDTO> getDeckByCardListAutoHero(@RequestParam("cardIds") List<String> cardIds) {
        DeckDTO result = deckService.getDeckByCardListAutoHero(cardIds);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-code")
    public ResponseEntity<DeckDTO> getDeckByCode(@RequestParam("code") String code) {
        DeckDTO result = deckService.getDeckByCode(code);
        return ResponseEntity.ok(result);
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
}

