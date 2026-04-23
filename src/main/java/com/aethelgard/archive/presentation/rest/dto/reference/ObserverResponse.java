package com.aethelgard.archive.presentation.rest.dto.reference;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Registro que representa la respuesta al probar el patrón Observer.
 * 
 * @param message Mensaje descriptivo del evento.
 * @param intensity Nivel de la perturbación mágica registrada.
 * @param note Notas adicionales para el archivero sobre dónde observar los resultados.
 */
@Schema(description = "Respuesta del patrón Observer")
public record ObserverResponse(
    @Schema(description = "Mensaje de resultado", example = "Tormenta simulada")
    String message,
    
    @Schema(description = "Intensidad de la perturbación mágica registrada", example = "5")
    int intensity,
    
    @Schema(description = "Notas adicionales para el archivero", example = "Revisa los logs de la consola")
    String note
) {}
