package com.aethelgard.archive.infrastructure.persistence.repository;

import com.aethelgard.archive.infrastructure.persistence.entity.MagicalArtifactJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtifactJpaRepository extends JpaRepository<MagicalArtifactJpaEntity, Long> {
}
