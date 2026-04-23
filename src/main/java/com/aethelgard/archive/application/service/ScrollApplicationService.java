package com.aethelgard.archive.application.service;

import com.aethelgard.archive.challenge.adapter.ModernSpellService;
import com.aethelgard.archive.domain.port.in.ReadScrollUseCase;
import org.springframework.stereotype.Service;

/**
 * Servicio de aplicación encargado de la lectura de pergaminos.
 * Actúa como puente para utilizar el sistema de adaptación de hechizos antiguos.
 */
@Service
public class ScrollApplicationService implements ReadScrollUseCase {

    private final ModernSpellService modernSpellService;

    public ScrollApplicationService(ModernSpellService modernSpellService) {
        this.modernSpellService = modernSpellService;
    }

    /**
     * Realiza la lectura de un pergamino heredado traduciéndolo al sistema moderno.
     * 
     * @return El nombre del hechizo descifrado.
     */
    @Override
    public String readLegacyScroll() {
        return modernSpellService.readSpell().getName();
    }
}
