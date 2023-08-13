package com.oscarjimenez.datamanageproject.domain.repository;

import com.oscarjimenez.datamanageproject.domain.entity.DeckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeckRepository extends JpaRepository<DeckEntity, UUID> {
}
