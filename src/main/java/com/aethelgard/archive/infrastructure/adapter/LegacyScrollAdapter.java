package com.aethelgard.archive.infrastructure.adapter;

import com.aethelgard.archive.challenge.adapter.LegacyRunicScroll;
import com.aethelgard.archive.challenge.adapter.ModernSpellService;
import com.aethelgard.archive.presentation.rest.dto.ModernSpellDTO;
import org.springframework.stereotype.Component;

/**
 * Desafío: El Adaptador de Pergaminos Arcanos.
 * Tu misión es construir un puente entre el sistema de archivos antiguo y la red moderna.
 */
@Component
public class LegacyScrollAdapter implements ModernSpellService {

    //TODO: MISION_ESCRIBA - Patrón Adapter
    // 1. Inyecta el componente `LegacyRunicScroll` por constructor (Inyección de Dependencias).
    // 2. Implementa el método `readSpell()` definido en ModernSpellService.
    // 3. Dentro de `readSpell()`:
    //    a) Llama a `legacyRunicScroll.readLegacyData()`.
    //    b) El resultado será: "ID:0892#MAGO:Melchior#SPELL:Tormenta de Escarcha#MANA:75"
    //    c) Debes extraer SOLO el nombre del hechizo ("Tormenta de Escarcha").
    //       Pista: Usa .split("#") o una expresión regular.
    //    d) Retorna un `new ModernSpellDTO(nombreExtraido)`.
    
    @Override
    public ModernSpellDTO readSpell() {
        throw new UnsupportedOperationException("//TODO: MISION_ESCRIBA");
    }
}
