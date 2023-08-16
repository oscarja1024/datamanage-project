package com.oscarjimenez.datamanageproject.domain.repository;

import com.oscarjimenez.datamanageproject.domain.entity.FavDeckEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavDeckRepository extends JpaRepository<FavDeckEntity, UUID> {

    List<FavDeckEntity> findByUser(UserEntity userId);

    FavDeckEntity findByDeckIdAndUser(UUID deckId, UserEntity user);
}
