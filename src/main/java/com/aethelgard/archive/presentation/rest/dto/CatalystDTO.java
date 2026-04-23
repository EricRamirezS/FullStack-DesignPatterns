package com.aethelgard.archive.presentation.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO que representa un catalizador místico invocado.
 */
@Schema(description = "Catalizador místico elemental")
public class CatalystDTO {
    
    @Schema(description = "Tipo de elemento del catalizador", example = "Fuego")
    private String type;
    
    public CatalystDTO() {}
    
    public CatalystDTO(String type) { this.type = type; }
    
    /**
     * Obtiene el tipo de catalizador.
     * @return Tipo elemental.
     */
    public String getType() { return type; }
    
    /**
     * Define el tipo de catalizador.
     * @param type Tipo elemental a establecer.
     */
    public void setType(String type) { this.type = type; }
}
