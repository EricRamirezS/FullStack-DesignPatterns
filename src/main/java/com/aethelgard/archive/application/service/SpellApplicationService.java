package com.aethelgard.archive.application.service;

import com.aethelgard.archive.challenge.observer.SpellAccessObserver;
import com.aethelgard.archive.domain.model.AncientSpell;
import com.aethelgard.archive.domain.port.in.FindSpellsUseCase;
import com.aethelgard.archive.domain.port.out.AncientSpellPort;
import com.aethelgard.archive.domain.port.out.SecurityLogPort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio de aplicación que orquesta la búsqueda y evaluación de hechizos.
 * Implementa el caso de uso para encontrar hechizos prohibidos y notifica a los observadores.
 */
@Service
public class SpellApplicationService implements FindSpellsUseCase {

    private final AncientSpellPort ancientSpellPort;
    private final SecurityLogPort securityLogPort;
    private final List<SpellAccessObserver> observers = new ArrayList<>();

    public SpellApplicationService(AncientSpellPort ancientSpellPort, SecurityLogPort securityLogPort) {
        this.ancientSpellPort = ancientSpellPort;
        this.securityLogPort = securityLogPort;
    }

    /**
     * Registra un nuevo observador en el sistema de vigilancia.
     * @param observer El observador (Escriba o Centinela) a registrar.
     */
    public void addObserver(SpellAccessObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Busca hechizos que superan un nivel de peligro y notifica a los observadores.
     * 
     * @param minLevel Nivel de peligro mínimo para considerar un hechizo como prohibido.
     * @param ipAddress Dirección IP del solicitante para registro de auditoría.
     * @return Lista de hechizos prohibidos encontrados.
     */
    @Override
    public List<AncientSpell> findForbiddenSpells(int minLevel, String ipAddress) {
        List<AncientSpell> spells = ancientSpellPort.findAll();
        
        for (AncientSpell spell : spells) {
            // Un hechizo se considera prohibido si su nivel es >= minLevel y además supera el umbral sagrado de 5
            if (spell.getDangerLevel() >= minLevel && spell.getDangerLevel() > 5) {
                // Notificar al puerto de infraestructura (registro persistente)
                securityLogPort.logUnauthorizedAccess(spell.getName(), spell.getDangerLevel(), ipAddress);
                
                // Notificar a todos los observadores registrados (Patrón Observer)
                observers.forEach(obs -> obs.onForbiddenAccess(spell.getName(), spell.getDangerLevel(), ipAddress));
            }
        }
        
        return spells.stream()
                .filter(s -> s.getDangerLevel() >= minLevel)
                .collect(Collectors.toList());
    }
}
