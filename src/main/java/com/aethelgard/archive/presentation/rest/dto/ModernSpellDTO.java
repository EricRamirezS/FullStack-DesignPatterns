package com.aethelgard.archive.presentation.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO que representa un hechizo moderno compatible con los nuevos sistemas de la Biblioteca.
 */
@Schema(description = "Representación moderna de un hechizo arcano")
public class ModernSpellDTO {
    
    @Schema(description = "Nombre místico del hechizo", example = "Explosión Solar")
    private String name;

    public ModernSpellDTO() {}

    public ModernSpellDTO(String name) {
        this.name = name;
    }

    /**
     * Obtiene el nombre del hechizo.
     * @return Nombre místico.
     */
    public String getName() { return name; }
    
    /**
     * Define el nombre del hechizo.
     * @param name Nombre místico a establecer.
     */
    public void setName(String name) { this.name = name; }
}
