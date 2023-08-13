package com.oscarjimenez.datamanageproject.domain.repository;

import com.oscarjimenez.datamanageproject.domain.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface GameRepository extends JpaRepository<GameEntity, UUID> {

    GameEntity findByGameIdAndUserId(UUID gameId, UUID userId);

}
