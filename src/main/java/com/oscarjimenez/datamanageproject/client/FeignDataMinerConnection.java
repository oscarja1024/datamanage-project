package com.oscarjimenez.datamanageproject.client;


import com.oscarjimenez.datamanageproject.client.DTOS.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "FeignDataMinerConnection", url = "${hearthstoneMiner.url}")
public interface FeignDataMinerConnection {

    @PostMapping(path="/metadata", produces = "application/json")
    MetadataResponseDTO getMetadata();

    @PostMapping(path="/decksByCardsAndHero",consumes = "application/json" , produces = "application/json")
    DeckDTO getDeckByCardListAndHero(@RequestBody MinerDTO request);

    @PostMapping(path="/decksByCards", produces = "application/json")
    DeckDTO getDeckByCardListAutoHero(@RequestBody MinerDTO request);

    @PostMapping(path="/decksByCode", produces = "application/json")
    DeckDTO getDeckByCode(@RequestBody MinerDTO request);

    @PostMapping(path="/oneCard", produces = "application/json")
    GetOneCardResponseDTO getOneCardById(@RequestBody MinerDTO request);

    @PostMapping(path="/cards", produces = "application/json")
    GetCardsResponseDTO getAllCards(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsSort", produces = "application/json")
    GetCardsResponseDTO getAllCardsSort(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsPageSize", produces = "application/json")
    GetCardsResponseDTO getAllCardsSetPageSize(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsPageSizeSort", produces = "application/json")
    GetCardsResponseDTO getAllCardsByPageSetPageSizeSort(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsByManaCost", produces = "application/json")
    GetCardsResponseDTO getAllCardsByManaCost(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsByManaCostAndAttack", consumes = "application/json", produces = "application/json")
    GetCardsResponseDTO getAllCardsByManaCostAndAttack(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsByAttack", consumes = "application/json", produces = "application/json")
    GetCardsResponseDTO getAllCardsByAttack(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsByType", consumes = "application/json", produces = "application/json")
    GetCardsResponseDTO getAllCardsByType(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsByAttackAndType", consumes = "application/json", produces = "application/json")
    GetCardsResponseDTO getAllCardsByTypeAndAttack(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsByAttackAndManaCost", consumes = "application/json", produces = "application/json")
    GetCardsResponseDTO getAllCardsByTypeAndManaCost(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsByAttackAndManaCostAndType", consumes = "application/json", produces = "application/json")
    GetCardsResponseDTO getAllCardsByTypeAndAttackAndManaCost(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsByHealth", consumes = "application/json", produces = "application/json")
    GetCardsResponseDTO getAllCardsByHealth(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsByGameMode", consumes = "application/json", produces = "application/json")
    GetCardsResponseDTO getAllCardsByGameMode(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsBySpellSchool", consumes = "application/json", produces = "application/json")
    GetCardsResponseDTO getAllCardsBySpellSchool(@RequestBody MinerDTO request);

    @PostMapping(path="/cardsBySet", consumes = "application/json", produces = "application/json")
    GetCardsResponseDTO getAllCardsBySet(@RequestBody MinerDTO request);
}
