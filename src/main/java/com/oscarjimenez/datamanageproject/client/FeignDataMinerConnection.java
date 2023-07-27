package com.oscarjimenez.datamanageproject.client;

import com.oscarjimenez.dataminerproject.api.DTOS.ControllerDTO.MinerDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetCardsResponseDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetOneCardResponseDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.MetadataResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "FeignDataMinerConnection", url = "${hearthstoneMiner.url}")
public interface FeignDataMinerConnection {

    @GetMapping(path="/hearthstoneMiner/metadata", produces = "application/json")
    MetadataResponseDTO getMetadata();

    @GetMapping(path="/hearthstoneMiner/decksByCardsAndHero",consumes = "application/json" , produces = "application/json")
    DeckDTO getDeckByCardListAndHero(@RequestBody MinerDTO request);

    @GetMapping(path="/hearthstoneMiner/decksByCards", produces = "application/json")
    DeckDTO getDeckByCardListAutoHero(@RequestBody MinerDTO request);

    @PostMapping(path="/hearthstoneMiner/decksByCode", produces = "application/json")
    DeckDTO getDeckByCode(@RequestBody MinerDTO request);

    @PostMapping(path="/hearthstoneMiner/oneCard", produces = "application/json")
    GetOneCardResponseDTO getOneCardById(@RequestBody MinerDTO request);

    @PostMapping(path="/hearthstoneMiner/cards", produces = "application/json")
    GetCardsResponseDTO getAllCards(@RequestBody MinerDTO request);

    @GetMapping(path="/hearthstoneMiner/cardsSort", produces = "application/json")
    Object getAllCardsSort(@RequestBody MinerDTO request);

    @GetMapping(path="/hearthstoneMiner/cardsPageSize", produces = "application/json")
    Object getAllCardsSetPageSize(@RequestBody MinerDTO request);

    @GetMapping(path="/hearthstoneMiner/cardsPageSizeSort", produces = "application/json")
    Object getAllCardsByPageSetPageSizeSort(@RequestBody MinerDTO request);

    @GetMapping(path="/hearthstoneMiner/cardsByManaCost", produces = "application/json")
    Object getAllCardsByManaCost(@RequestBody MinerDTO request);

    @GetMapping(path="/cardsByManaCostAndAttack", consumes = "application/json", produces = "application/json")
    Object getAllCardsByManaCostAndAttack(@RequestBody MinerDTO request);

    @GetMapping(path="/cardsByAttack", consumes = "application/json", produces = "application/json")
    Object getAllCardsByAttack(@RequestBody MinerDTO request);

    @GetMapping(path="/cardsByType", consumes = "application/json", produces = "application/json")
    Object getAllCardsByType(@RequestBody Object request);

    @GetMapping(path="/cardsByAttackAndType", consumes = "application/json", produces = "application/json")
    Object getAllCardsByTypeAndAttack(@RequestBody MinerDTO request);

    @GetMapping(path="/cardsByAttackAndManaCost", consumes = "application/json", produces = "application/json")
    Object getAllCardsByTypeAndManaCost(@RequestBody MinerDTO request);

    @GetMapping(path="/cardsByAttackAndManaCostAndType", consumes = "application/json", produces = "application/json")
    Object getAllCardsByTypeAndAttackAndManaCost(@RequestBody MinerDTO request);

    @GetMapping(path="/cardsByHealth", consumes = "application/json", produces = "application/json")
    Object getAllCardsByHealth(@RequestBody MinerDTO request);

    @GetMapping(path="/cardsByGameMode", consumes = "application/json", produces = "application/json")
    Object getAllCardsByGameMode(@RequestBody MinerDTO request);

    @GetMapping(path="/cardsBySpellSchool", consumes = "application/json", produces = "application/json")
    Object getAllCardsBySpellSchool(@RequestBody MinerDTO request);

    @GetMapping(path="/cardsBySet", consumes = "application/json", produces = "application/json")
    Object getAllCardsBySet(@RequestBody MinerDTO request);
}
