package com.oscarjimenez.datamanageproject.domain.repository;

import com.oscarjimenez.datamanageproject.domain.entity.GameEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface GameRepository extends JpaRepository<GameEntity, UUID> {

    List<GameEntity> findByUser(UserEntity userId);

    GameEntity findByGameIdAndUser(UUID gameId, UserEntity user);

}
