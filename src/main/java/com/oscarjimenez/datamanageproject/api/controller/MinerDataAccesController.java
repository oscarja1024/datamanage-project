package com.oscarjimenez.datamanageproject.api.controller;

import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.GetCardsResponseDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.GetOneCardResponseDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.MetadataResponseDTO;
import com.oscarjimenez.datamanageproject.service.CardDataFinderService;
import com.oscarjimenez.datamanageproject.service.DTO.AttackDTO;
import com.oscarjimenez.datamanageproject.service.DTO.HealthDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ManaDTO;
import com.oscarjimenez.datamanageproject.service.DTO.SortDTO;
import com.oscarjimenez.datamanageproject.service.DeckFinderDataService;
import com.oscarjimenez.datamanageproject.service.MetadataFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/miner")
public class MinerDataAccesController {

    @Autowired
    private CardDataFinderService cardDataService;

    @Autowired
    private DeckFinderDataService deckDataService;

    @Autowired
    private MetadataFinderService metadataFinderService;

    @GetMapping("/metadata")
    public ResponseEntity<MetadataResponseDTO> getAllMetadata() {
        MetadataResponseDTO metadata = metadataFinderService.getAllMetada();
        return ResponseEntity.ok(metadata);
    }

    @GetMapping("/byCardListAndHero")
    public ResponseEntity<DeckDTO> getDeckByCardListAndHero(@RequestBody String cardIds,
                                                            @RequestParam("heroId") String heroId) {
        DeckDTO deck = deckDataService.getDeckByCardListAndHero(cardIds, heroId);
        return ResponseEntity.ok(deck);
    }

    @GetMapping("/byCardListAutoHero")
    public ResponseEntity<DeckDTO> getDeckByCardListAutoHero(@RequestBody String cardIds) {
        DeckDTO deck = deckDataService.getDeckByCardListAutoHero(cardIds);
        return ResponseEntity.ok(deck);
    }

    @GetMapping("/byCode")
    public ResponseEntity<DeckDTO> getDeckByCode(@RequestParam("code") String code) {
        DeckDTO deck = deckDataService.getDeckByCode(code);
        return ResponseEntity.ok(deck);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<GetOneCardResponseDTO> getOneCardById(@PathVariable("cardId") String cardId) {
        GetOneCardResponseDTO card = cardDataService.getOneCardById(cardId);
        return ResponseEntity.ok(card);
    }

    @GetMapping("/cards/{page}")
    public ResponseEntity<GetCardsResponseDTO> getAllCards(@PathVariable String page) {
        var cards = cardDataService.getAllCards(page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/sort")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsSort(@RequestParam("sort") String sort, @RequestParam("page") String page) {
        var sortDTO =  SortDTO.builder().sort(sort).build();
        GetCardsResponseDTO cards = cardDataService.getAllCardsSort(sortDTO, page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/pageSize")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsSetPageSize(@RequestParam("pageSize") String pageSize, @RequestParam("page") String page) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsSetPageSize(pageSize, page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/sortPageSize")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByPageSetPageSizeSort(@RequestParam("sort") String sort,
                                                                                @RequestParam("pageSize") String pageSize, @RequestParam("page") String page) {
        var sortDTO =  SortDTO.builder().sort(sort).build();
        GetCardsResponseDTO cards = cardDataService.getAllCardsByPageSetPageSizeSort(sortDTO, pageSize, page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/mana/{manaCost}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByManaCost(@PathVariable("manaCost") String mana, @RequestParam("page") String page) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByManaCost(ManaDTO.builder().manaCost(mana).build(), page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/mana/{manaCost}/attack/{attack}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByManaCostAndAttack(@PathVariable("manaCost") String mana,
                                                                              @PathVariable("attack") String attack , @RequestParam("page") String page) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByManaCostAndAttack(ManaDTO.builder().manaCost(mana).build(), AttackDTO.builder().attack(attack).build(), page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/attack/{attack}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByAttack(@PathVariable("attack") String attack, @RequestParam("page") String page) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByAttack(AttackDTO.builder().attack(attack).build(), page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/type/{cardType}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByType(@PathVariable("cardType") String cardType, @RequestParam("page") String page) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByType(cardType, page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/type/{cardType}/attack/{attack}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByTypeAndAttack(@PathVariable("cardType") String cardType,
                                                                          @PathVariable("attack") String attack, @RequestParam("page") String page) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByTypeAndAttack(cardType, AttackDTO.builder().attack(attack).build(), page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/type/{cardType}/mana/{manaCost}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByTypeAndManaCost(@PathVariable("cardType") String cardType,
                                                                            @PathVariable("manaCost") String mana, @RequestParam("page") String page) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByTypeAndManaCost(cardType, ManaDTO.builder().manaCost(mana).build(), page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/type/{cardType}/mana/{manaCost}/attack/{attack}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByTypeAndAttackAndManaCost(@PathVariable("cardType") String cardType,
                                                                                     @PathVariable("manaCost") String mana,
                                                                                     @PathVariable("attack") String attack, @RequestParam("page") String page) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByTypeAndAttackAndManaCost(cardType, ManaDTO.builder().manaCost(mana).build(), AttackDTO.builder().attack(attack).build(), page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/health/{health}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByHealth(@PathVariable("health") String health, @RequestParam("page") String page) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByHealth(HealthDTO.builder().health(health).build(), page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/gameMode/{gameMode}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByGameMode(@PathVariable("gameMode") String gameMode, @RequestParam("page") String page) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByGameMode(gameMode, page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/spellSchool/{spellSchool}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsBySpellSchool(@PathVariable("spellSchool") String spellSchool, @RequestParam("page") String page) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsBySpellSchool(spellSchool, page);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/cardSet/{cardSet}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsBySet(@PathVariable("cardSet") String cardSet, @RequestParam("page") String page) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsBySet(cardSet, page);
        return ResponseEntity.ok(cards);
    }
}
