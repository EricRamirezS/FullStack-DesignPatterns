package com.aethelgard.archive.domain.model;

/**
 * Modelo que representa un Artefacto Mágico de Aethelgard.
 */
public class MagicalArtifact {
    private Long id;
    private String name;
    private String description;

    public MagicalArtifact() {}

    public MagicalArtifact(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Obtiene el identificador único del artefacto.
     * @return ID numérico.
     */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    /**
     * Obtiene el nombre del artefacto.
     * @return Nombre místico.
     */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /**
     * Obtiene la descripción histórica y funcional del artefacto.
     * @return Descripción textual.
     */
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
