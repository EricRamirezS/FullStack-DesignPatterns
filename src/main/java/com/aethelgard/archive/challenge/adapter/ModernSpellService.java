package com.aethelgard.archive.challenge.adapter;

import com.aethelgard.archive.presentation.rest.dto.ModernSpellDTO;

/**
 * Interfaz Objetivo (Target): Define el estándar moderno que la Biblioteca espera.
 */
public interface ModernSpellService {
    
    /**
     * Obtiene la representación de un hechizo en el formato moderno.
     * @return DTO del hechizo moderno.
     */
    ModernSpellDTO readSpell();
}
