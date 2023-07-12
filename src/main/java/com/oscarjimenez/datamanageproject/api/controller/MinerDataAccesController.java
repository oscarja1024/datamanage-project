package com.oscarjimenez.datamanageproject.api.controller;

import com.oscarjimenez.datamanageproject.service.CardDataFinderService;
import com.oscarjimenez.datamanageproject.service.DTO.AttackDTO;
import com.oscarjimenez.datamanageproject.service.DTO.HealthDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ManaDTO;
import com.oscarjimenez.datamanageproject.service.DTO.SortDTO;
import com.oscarjimenez.datamanageproject.service.DeckFinderDataService;
import com.oscarjimenez.datamanageproject.service.MetadataFinderService;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetCardsResponseDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetOneCardResponseDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.MetadataResponseDTO;
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
    public ResponseEntity<DeckDTO> getDeckByCardListAndHero(@RequestParam("cardIds") List<String> cardIds,
                                                            @RequestParam("heroId") String heroId) {
        DeckDTO deck = deckDataService.getDeckByCardListAndHero(cardIds, heroId);
        return ResponseEntity.ok(deck);
    }

    @GetMapping("/byCardListAutoHero")
    public ResponseEntity<DeckDTO> getDeckByCardListAutoHero(@RequestParam("cardIds") List<String> cardIds) {
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

    @GetMapping("cards")
    public ResponseEntity<List<GetCardsResponseDTO>> getAllCards() {
        List<GetCardsResponseDTO> cards = cardDataService.getAllCards();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/sort")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsSort(@RequestParam("sort") String sort) {
        var sortDTO =  SortDTO.builder().sort(sort).build();
        GetCardsResponseDTO cards = cardDataService.getAllCardsSort(sortDTO);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/pageSize")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsSetPageSize(@RequestParam("pageSize") String pageSize) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsSetPageSize(pageSize);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/sortPageSize")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByPageSetPageSizeSort(@RequestParam("sort") String sort,
                                                                                @RequestParam("pageSize") String pageSize) {
        var sortDTO =  SortDTO.builder().sort(sort).build();
        GetCardsResponseDTO cards = cardDataService.getAllCardsByPageSetPageSizeSort(sortDTO, pageSize);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/mana/{manaCost}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByManaCost(@PathVariable("manaCost") ManaDTO mana) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByManaCost(mana);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/mana/{manaCost}/attack/{attack}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByManaCostAndAttack(@PathVariable("manaCost") ManaDTO mana,
                                                                              @PathVariable("attack") AttackDTO attack) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByManaCostAndAttack(mana, attack);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/attack/{attack}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByAttack(@PathVariable("attack") AttackDTO attack) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByAttack(attack);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/type/{cardType}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByType(@PathVariable("cardType") String cardType) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByType(cardType);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/type/{cardType}/attack/{attack}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByTypeAndAttack(@PathVariable("cardType") String cardType,
                                                                          @PathVariable("attack") AttackDTO attack) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByTypeAndAttack(cardType, attack);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/type/{cardType}/mana/{manaCost}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByTypeAndManaCost(@PathVariable("cardType") String cardType,
                                                                            @PathVariable("manaCost") ManaDTO mana) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByTypeAndManaCost(cardType, mana);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/type/{cardType}/mana/{manaCost}/attack/{attack}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByTypeAndAttackAndManaCost(@PathVariable("cardType") String cardType,
                                                                                     @PathVariable("manaCost") ManaDTO mana,
                                                                                     @PathVariable("attack") AttackDTO attack) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByTypeAndAttackAndManaCost(cardType, mana, attack);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/health/{health}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByHealth(@PathVariable("health") HealthDTO health) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByHealth(health);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/gameMode/{gameMode}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsByGameMode(@PathVariable("gameMode") String gameMode) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsByGameMode(gameMode);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/spellSchool/{spellSchool}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsBySpellSchool(@PathVariable("spellSchool") String spellSchool) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsBySpellSchool(spellSchool);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/cardSet/{cardSet}")
    public ResponseEntity<GetCardsResponseDTO> getAllCardsBySet(@PathVariable("cardSet") String cardSet) {
        GetCardsResponseDTO cards = cardDataService.getAllCardsBySet(cardSet);
        return ResponseEntity.ok(cards);
    }
}
