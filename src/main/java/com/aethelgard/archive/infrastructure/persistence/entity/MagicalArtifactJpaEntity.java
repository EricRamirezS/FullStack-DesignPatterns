package com.aethelgard.archive.infrastructure.persistence.entity;

import com.aethelgard.archive.domain.model.MagicalArtifact;
import jakarta.persistence.*;

/**
 * Entidad JPA que representa la persistencia de un Artefacto Mágico.
 */
@Entity
@Table(name = "magical_artifacts")
public class MagicalArtifactJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public MagicalArtifactJpaEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    /**
     * Convierte esta entidad al modelo de dominio.
     * @return El modelo de dominio MagicalArtifact.
     */
    public MagicalArtifact toDomain() {
        return new MagicalArtifact(id, name, description);
    }
    
    /**
     * Crea una entidad a partir de un modelo de dominio.
     * @param model El modelo de dominio.
     * @return La entidad JPA correspondiente.
     */
    public static MagicalArtifactJpaEntity fromDomain(MagicalArtifact model) {
        if(model == null) return null;
        MagicalArtifactJpaEntity entity = new MagicalArtifactJpaEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        return entity;
    }
}
