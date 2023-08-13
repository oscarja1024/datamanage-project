package com.oscarjimenez.datamanageproject.domain.entity;

import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Table(name = "DECK_REPORT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID deckReportId;

    @Column
    private UUID deckId;

    @Column
    private UUID userId;

    @Column
    private String manaMean;

    @Column
    private String attackMean;

    @Column
    private String puntuation;

    @Column
    private String spellsMean;

    @Column
    private String healthMean;

    @Column
    private String minionCount;

    @Column
    private String cardCount;

    @Column
    private String posibilityOfOpponentCardStole;

    @Column
    private String posibilityOfExtraCardStole;

    @Column
    private String healMean;

    @Column
    private String secretsCount;

    @Column
    private String cardCreation;

    @Column
    private String attackIncrease;

}
