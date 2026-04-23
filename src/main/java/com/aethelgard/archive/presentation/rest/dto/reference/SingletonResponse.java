package com.aethelgard.archive.presentation.rest.dto.reference;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Registro que representa la respuesta al probar el patrón Singleton.
 * 
 * @param message Mensaje descriptivo del resultado.
 * @param isSameMemoryInstance Indica si las instancias comparadas son físicamente la misma en memoria.
 */
@Schema(description = "Respuesta del patrón Singleton")
public record SingletonResponse(
    @Schema(description = "Mensaje de resultado", example = "Instancias obtenidas")
    String message,
    
    @Schema(description = "Indica si ambas referencias apuntan al mismo nodo de memoria", example = "true")
    boolean isSameMemoryInstance
) {}
