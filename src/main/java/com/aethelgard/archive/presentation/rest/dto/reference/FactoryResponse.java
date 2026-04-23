package com.aethelgard.archive.presentation.rest.dto.reference;

import com.aethelgard.archive.reference.factory.IPotion;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Registro que representa la respuesta al probar el patrón Factory.
 * 
 * @param message Mensaje descriptivo del resultado.
 * @param potionClass Nombre de la clase concreta instanciada por la fábrica.
 * @param potion Instancia de la poción creada.
 */
@Schema(description = "Respuesta del patrón Factory")
public record FactoryResponse(
        @Schema(description = "Mensaje de resultado", example = "Poción creada correctamente") String message,

        @Schema(description = "Nombre de la clase de la poción generada", example = "HealthPotion") String potionClass,

        @Schema(description = "Detalles de la poción creada") IPotion potion) {
}
