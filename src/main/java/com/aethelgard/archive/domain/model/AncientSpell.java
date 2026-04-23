package com.aethelgard.archive.domain.model;

/**
 * Modelo que representa un Hechizo Antiguo recuperado de los archivos.
 */
public class AncientSpell {
    private Long id;
    private String name;
    private int dangerLevel;
    private MagicalArtifact artifact;

    public AncientSpell() {}

    public AncientSpell(Long id, String name, int dangerLevel, MagicalArtifact artifact) {
        this.id = id;
        this.name = name;
        this.dangerLevel = dangerLevel;
        this.artifact = artifact;
    }

    /**
     * Obtiene el identificador único del hechizo.
     * @return ID numérico.
     */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    /**
     * Obtiene el nombre místico del hechizo.
     * @return Nombre del hechizo.
     */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /**
     * Obtiene el nivel de peligro arcano (0-10).
     * @return Nivel de peligro.
     */
    public int getDangerLevel() { return dangerLevel; }
    public void setDangerLevel(int dangerLevel) { this.dangerLevel = dangerLevel; }

    /**
     * Obtiene el artefacto vinculado a este hechizo.
     * @return El artefacto místico.
     */
    public MagicalArtifact getArtifact() { return artifact; }
    public void setArtifact(MagicalArtifact artifact) { this.artifact = artifact; }
}
