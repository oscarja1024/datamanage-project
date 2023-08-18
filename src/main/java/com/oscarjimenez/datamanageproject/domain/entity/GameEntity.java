package com.oscarjimenez.datamanageproject.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oscarjimenez.datamanageproject.client.DTOS.ClassDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.GetOneCardResponseDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.HeroDTO;
import com.oscarjimenez.datamanageproject.service.DTO.DamageDTO;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;
import com.oscarjimenez.datamanageproject.service.DTO.UserAnnotationsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "GAME_DATA")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID gameId;

    @Column
    private boolean result; //IF TRUE VICTORIA , IF FALSE DERROTA

    @Column
    private String manaCostOfGame;

    @Column
    private String damageToHero;

    @Column
    private String damageToMinions;

    @Column
    private String DamageToHeroPerRound;

    @Column
    private String DamageToMinionsXRound;

    @Column
    private int numberOfRounds;

    @Column
    private String cardsStolePerRound;

    @Column
    private String cardsStoleInGame;

    @Column
    private String healthHealToHero;

    @Column
    private String healthHealInGame;

    @Column
    private String heroHabilityUse;

    @Column
    private String cardsStoleToOpponent;

    @Column
    private String numberOfSpellsUsed;

    @Column
    private String userAnnotationsDTO;

    @Column
    private String deckPuntuationUtility;

    @Column
    private UUID deckUsedInGame;

    @Column
    private String deckCode;

    @Column
    private String version;

    @Column
    private String format;

    @Column
    private String heroId;

    @Column
    private String heroPower;

    @Column
    private String cardIds;

    @Column
    private int cardCount;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReport;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

}
