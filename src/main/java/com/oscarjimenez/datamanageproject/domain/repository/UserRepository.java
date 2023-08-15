package com.oscarjimenez.datamanageproject.domain.repository;

import com.oscarjimenez.datamanageproject.domain.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    List<CardEntity> findCardsByUserId(UUID userId);

    List<DeckEntity> findDeckReportsByUserId(UUID userId);

    List<FavDeckEntity> findDecksByUserId(UUID userId);

    List<GameEntity> findGamesByUserId(UUID userId);
}
