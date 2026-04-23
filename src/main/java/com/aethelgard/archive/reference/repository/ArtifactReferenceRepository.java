package com.aethelgard.archive.reference.repository;

import com.aethelgard.archive.infrastructure.persistence.entity.MagicalArtifactJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtifactReferenceRepository extends JpaRepository<MagicalArtifactJpaEntity, Long> {
    List<MagicalArtifactJpaEntity> findByNameContainingIgnoreCase(String name);
}
