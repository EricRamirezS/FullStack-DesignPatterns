package com.aethelgard.archive.domain.port.out;

import com.aethelgard.archive.domain.model.MagicalArtifact;
import java.util.Optional;

public interface ArtifactRepositoryPort {
    Optional<MagicalArtifact> findById(Long id);
}
