package com.oscarjimenez.datamanageproject.api.controller;

import com.oscarjimenez.datamanageproject.domain.service.GameUserCardDataService;
import com.oscarjimenez.datamanageproject.service.CardDataClasifierService;
import com.oscarjimenez.datamanageproject.service.DTO.ResultCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserDataAccesController {

    @Autowired
    private CardDataClasifierService cardService;

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

