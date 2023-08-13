package com.oscarjimenez.datamanageproject.domain.repository;

import com.oscarjimenez.datamanageproject.domain.entity.GameEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface GameRepository extends JpaRepository<GameEntity, UUID> {

    GameEntity findByGameIdAndUser(UUID gameId, UserEntity user);

}
