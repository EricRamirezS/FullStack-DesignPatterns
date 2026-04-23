package com.aethelgard.archive.challenge.factory;

import org.springframework.stereotype.Component;

/**
 * Desafío: La Forja de Catalizadores.
 * Implementa la fábrica para crear catalizadores elementales.
 */
@Component
public class CatalystFactory {
//TODO: MISION_ESCRIBA - Patrón Factory Method
// Implementa `public ICatalyst createCatalyst(String type)`:
//   - "FIRE"  → new FireCatalyst()
//   - "FROST" → new FrostCatalyst()
//   - "VOID"  → new VoidCatalyst()
//   - cualquier otro valor → lanza IllegalArgumentException("Catalizador desconocido: " + type)
// Pista: estudia PotionFactory.java en el paquete reference.

    /**
     * Crea un catalizador místico basado en el tipo de elemento.
     * 
     * @param type El tipo elemental (FIRE, FROST, VOID).
     * @return Una instancia concreta de ICatalyst.
     * @throws IllegalArgumentException Si el elemento no es reconocido.
     */
    public ICatalyst createCatalyst(String type) {
        throw new UnsupportedOperationException("//TODO: MISION_ESCRIBA");
    }
}
