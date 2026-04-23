package com.aethelgard.archive.reference.factory;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class PotionFactory {
    public IPotion createPotion(PotionType type) {
        try {
            // Convierte "HEALTH" en "HealthPotion"
            String className = type.name().charAt(0) + type.name().substring(1).toLowerCase() + "Potion";

            // Busca la clase dinámicamente e instancia una nueva
            String fullPath = "com.aethelgard.archive.reference.factory." + className;
            return (IPotion) Class.forName(fullPath).getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            throw new IllegalArgumentException("No se encontró la receta para: " + type, e);
        }
    }
}
