package com.oscarjimenez.datamanageproject.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "FAV_DECK_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavDeckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID deckId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    @Column
    private String cardIds;

    @Column
    private String heroId;

}
