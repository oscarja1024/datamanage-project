package com.oscarjimenez.datamanageproject.client;

import com.oscarjimenez.dataminerproject.api.DTOS.ControllerDTO.MinerDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "FeignDataMinerConnection", url = "${hearthstoneMiner.url}")
public interface FeignDataMinerConnection {
    @GetMapping(path="/hearthstoneMiner/metadata", produces = "application/json")
    Object getMetadata();

    @GetMapping(path="/hearthstoneMiner/cardBacksById", produces = "application/json")
    Object getCardsBackById(@RequestBody Object request);

    @GetMapping(path="/hearthstoneMiner/cardBacksByCategory", produces = "application/json")
    Object getCardBacksByCategory(@RequestBody Object request);

    @GetMapping(path="/hearthstoneMiner/cardBacks", produces = "application/json")
    Object getCardBacks(@RequestBody Object request);

    @GetMapping(path="/hearthstoneMiner/cardBacksSort", produces = "application/json")
    Object getCardBacksSort(@RequestBody Object request);

    @GetMapping(path="/hearthstoneMiner/cardBacksByCategorySort", produces = "application/json")
    DeckDTO getCardBacksCategorySort(@RequestBody MinerDTO request);

    @GetMapping(path="/hearthstoneMiner/decksByCardsAndHero", produces = "application/json")
    DeckDTO getDeckByCardListAndHero(@RequestBody MinerDTO request);

    @GetMapping(path="/hearthstoneMiner/decksByCards", produces = "application/json")
    DeckDTO getDeckByCardListAutoHero(@RequestBody MinerDTO request);

    @GetMapping(path="/hearthstoneMiner/decksByCode", produces = "application/json")
    DeckDTO getDeckByCode(@RequestBody MinerDTO request);

    @GetMapping(path="/hearthstoneMiner/oneCard", produces = "application/json")
    Object getOneCardById(@RequestBody Object request);

    @GetMapping(path="/hearthstoneMiner/cards", produces = "application/json")
    Object getAllCards(@RequestBody Object request);

    @GetMapping(path="/hearthstoneMiner/cardsSort", produces = "application/json")
    Object getAllCardsSort(@RequestBody Object request);

    @GetMapping(path="/hearthstoneMiner/cardsPageSize", produces = "application/json")
    Object getAllCardsSetPageSize(@RequestBody Object request);

    @GetMapping(path="/hearthstoneMiner/cardsPageSizeSort", produces = "application/json")
    Object getAllCardsByPageSetPageSizeSort(@RequestBody Object request);

    @GetMapping(path="/hearthstoneMiner/cardsByManaCost", produces = "application/json")
    Object getAllCardsByManaCost(@RequestBody Object request);

    @GetMapping(path="/cardsByManaCostAndAttack", consumes = "application/json", produces = "application/json")
    Object getAllCardsByManaCostAndAttack(@RequestBody Object request);

    @GetMapping(path="/cardsByAttack", consumes = "application/json", produces = "application/json")
    Object getAllCardsByAttack(@RequestBody Object request);

    @GetMapping(path="/cardsByType", consumes = "application/json", produces = "application/json")
    Object getAllCardsByType(@RequestBody Object request);

    @GetMapping(path="/cardsByAttackAndType", consumes = "application/json", produces = "application/json")
    Object getAllCardsByTypeAndAttack(@RequestBody Object request);

    @GetMapping(path="/cardsByAttackAndManaCost", consumes = "application/json", produces = "application/json")
    Object getAllCardsByTypeAndManaCost(@RequestBody Object request);

    @GetMapping(path="/cardsByAttackAndManaCostAndType", consumes = "application/json", produces = "application/json")
    Object getAllCardsByTypeAndAttackAndManaCost(@RequestBody Object request);

    @GetMapping(path="/cardsByHealth", consumes = "application/json", produces = "application/json")
    Object getAllCardsByHealth(@RequestBody Object request);

    @GetMapping(path="/cardsByGameMode", consumes = "application/json", produces = "application/json")
    Object getAllCardsByGameMode(@RequestBody Object request);

    @GetMapping(path="/cardsBySpellSchool", consumes = "application/json", produces = "application/json")
    Object getAllCardsBySpellSchool(@RequestBody Object request);

    @GetMapping(path="/cardsBySet", consumes = "application/json", produces = "application/json")
    Object getAllCardsBySet(@RequestBody Object request);
}
