package com.aethelgard.archive.challenge.adapter;

import org.springframework.stereotype.Component;

/**
 * Clase Heredada (Adaptee): Representa un sistema de archivo rúnico de hace siglos.
 * Devuelve tramas de texto planas y complejas que los sistemas modernos no entienden.
 */
@Component
public class LegacyRunicScroll {
    
    /**
     * Recupera una trama de datos cruda desde los archivos antiguos.
     * Formato legado: [ID]#[AUTOR]#[NOMBRE_HECHIZO]#[MANA]
     * @return Trama de texto incompatible.
     */
    public String readLegacyData() {
        return "ID:0892#MAGO:Melchior#SPELL:Tormenta de Escarcha#MANA:75";
    }
}
