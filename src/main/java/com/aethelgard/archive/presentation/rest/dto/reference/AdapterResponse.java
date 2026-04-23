package com.aethelgard.archive.presentation.rest.dto.reference;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta del patrón Adapter")
public record AdapterResponse(
    @Schema(description = "Mensaje de resultado", example = "Valor adaptado")
    String message,
    
    @Schema(description = "Valor mágico adaptado a formato moderno", example = "9000")
    int adaptedValue
) {}
