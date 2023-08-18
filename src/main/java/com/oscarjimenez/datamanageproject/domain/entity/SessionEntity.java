package com.oscarjimenez.datamanageproject.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "SESSIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID sessionId;

    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

}
