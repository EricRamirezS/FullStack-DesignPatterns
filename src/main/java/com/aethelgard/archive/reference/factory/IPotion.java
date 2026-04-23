package com.aethelgard.archive.reference.factory;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "Interfaz base para todas las pociones mágicas generadas por la fábrica",
    anyOf = {HealthPotion.class, ManaPotion.class}
)
public interface IPotion {
    
    @Schema(description = "Obtiene el tipo de poción", example = "HEALTH")
    String getType();
    
    @Schema(description = "Obtiene la cantidad de puntos que restaura esta poción", example = "50")
    int getRestoreAmount();
}
