package com.aethelgard.archive.challenge.singleton;

/**
 * Desafío: El Oráculo Arcano.
 * Tu misión es asegurar que solo exista una instancia del Oráculo en toda la Biblioteca.
 */
public class ArcaneOracle {
//TODO: MISION_ESCRIBA - Patrón Singleton
// 1. Declara una variable estática privada `volatile` de tipo ArcaneOracle llamada `instance`.
// 2. Haz el constructor PRIVATE (el Concilio usará Reflection para verificarlo).
// 3. Implementa `public static ArcaneOracle getInstance()` con double-checked locking.
// Pista: estudia ManaGridConfig.java en el paquete reference.

    /**
     * Obtiene la instancia única del Oráculo Arcano.
     * @return La instancia alineada del Oráculo.
     */
    public static ArcaneOracle getInstance() {
        throw new UnsupportedOperationException("//TODO: MISION_ESCRIBA");
    }
}
