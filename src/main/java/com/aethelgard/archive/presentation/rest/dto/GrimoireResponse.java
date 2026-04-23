package com.aethelgard.archive.presentation.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Registro que representa una respuesta genérica del sistema de la Biblioteca.
 * @param result El mensaje o resultado de la operación mística.
 */
@Schema(description = "Respuesta genérica del Grimorio de Aethelgard")
public record GrimoireResponse(
    @Schema(description = "Mensaje o resultado de la operación mágica", example = "El Oráculo está ALINEADO.")
    String result
) {}
