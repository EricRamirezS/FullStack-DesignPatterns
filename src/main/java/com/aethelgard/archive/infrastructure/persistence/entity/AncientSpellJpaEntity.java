package com.aethelgard.archive.infrastructure.persistence.entity;

import com.aethelgard.archive.domain.model.AncientSpell;
import jakarta.persistence.*;

/**
 * Entidad JPA que representa la persistencia de un Hechizo Antiguo en la base de datos.
 */
@Entity
@Table(name = "ancient_spells")
public class AncientSpellJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "danger_level", nullable = false)
    private int dangerLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artifact_id")
    private MagicalArtifactJpaEntity artifact;

    public AncientSpellJpaEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getDangerLevel() { return dangerLevel; }
    public void setDangerLevel(int dangerLevel) { this.dangerLevel = dangerLevel; }
    public MagicalArtifactJpaEntity getArtifact() { return artifact; }
    public void setArtifact(MagicalArtifactJpaEntity artifact) { this.artifact = artifact; }

    /**
     * Convierte esta entidad de persistencia al modelo de dominio puro.
     * @return El modelo de dominio AncientSpell.
     */
    public AncientSpell toDomain() {
        return new AncientSpell(
            id, 
            name, 
            dangerLevel, 
            artifact != null ? artifact.toDomain() : null
        );
    }

    /**
     * Crea una entidad de persistencia a partir de un modelo de dominio.
     * @param model El modelo de dominio.
     * @return La entidad JPA correspondiente.
     */
    public static AncientSpellJpaEntity fromDomain(AncientSpell model) {
        if(model == null) return null;
        AncientSpellJpaEntity entity = new AncientSpellJpaEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDangerLevel(model.getDangerLevel());
        if(model.getArtifact() != null) {
            entity.setArtifact(MagicalArtifactJpaEntity.fromDomain(model.getArtifact()));
        }
        return entity;
    }
}
