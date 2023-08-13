package com.oscarjimenez.datamanageproject.domain.repository;

import com.oscarjimenez.datamanageproject.domain.entity.FavDeckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FavDeckRepository extends JpaRepository<FavDeckEntity, UUID> {


    FavDeckEntity findByDeckIdAndUserId(UUID deckId, UUID userId);
}
