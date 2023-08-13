package com.oscarjimenez.datamanageproject.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CARD_DATA")
@Builder
public class CardEntity {

    @Id
    private String idorSlug;

    @Column
    private UUID userId;

}
