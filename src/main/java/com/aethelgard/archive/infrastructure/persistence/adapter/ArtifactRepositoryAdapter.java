package com.aethelgard.archive.infrastructure.persistence.adapter;

import com.aethelgard.archive.domain.model.MagicalArtifact;
import com.aethelgard.archive.domain.port.out.ArtifactRepositoryPort;
import com.aethelgard.archive.infrastructure.persistence.entity.MagicalArtifactJpaEntity;
import com.aethelgard.archive.infrastructure.persistence.repository.ArtifactJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ArtifactRepositoryAdapter implements ArtifactRepositoryPort {

    private final ArtifactJpaRepository artifactJpaRepository;

    public ArtifactRepositoryAdapter(ArtifactJpaRepository artifactJpaRepository) {
        this.artifactJpaRepository = artifactJpaRepository;
    }

    @Override
    public Optional<MagicalArtifact> findById(Long id) {
        return artifactJpaRepository.findById(id)
                .map(MagicalArtifactJpaEntity::toDomain);
    }

    // Required for read-only validation test
    public void save(MagicalArtifact artifact) {
        artifactJpaRepository.save(com.aethelgard.archive.infrastructure.persistence.entity.MagicalArtifactJpaEntity.fromDomain(artifact));
    }
}
