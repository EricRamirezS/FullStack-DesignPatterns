package com.aethelgard.archive.infrastructure.persistence.adapter;

import com.aethelgard.archive.domain.model.AncientSpell;
import com.aethelgard.archive.domain.port.out.AncientSpellPort;
import com.aethelgard.archive.domain.port.out.SpellRepositoryPort;
import com.aethelgard.archive.infrastructure.persistence.repository.SpellJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador de persistencia para la gestión de hechizos antiguos.
 * Implementa tanto el puerto específico de búsqueda como el puerto general de recuperación.
 */
@Component
public class SpellRepositoryAdapter implements SpellRepositoryPort, AncientSpellPort {

    private final SpellJpaRepository spellJpaRepository;

    public SpellRepositoryAdapter(SpellJpaRepository spellJpaRepository) {
        this.spellJpaRepository = spellJpaRepository;
    }

    /**
     * Recupera todos los hechizos antiguos de la gran biblioteca.
     */
    @Override
    public List<AncientSpell> findAll() {
        return spellJpaRepository.findAll().stream()
                .map(entity -> entity.toDomain())
                .collect(Collectors.toList());
    }

    @Override
    public List<AncientSpell> findByDangerLevelGreaterThan(int dangerLevel) {
        // Este método forma parte del desafío de los estudiantes en SpellJpaRepository.
        return spellJpaRepository.findByDangerLevelGreaterThan(dangerLevel).stream()
                .map(entity -> entity.toDomain())
                .collect(Collectors.toList());
    }

    /**
     * Intenta guardar un hechizo (usado para probar la restricción de solo lectura).
     */
    public void save(AncientSpell spell) {
        spellJpaRepository.save(com.aethelgard.archive.infrastructure.persistence.entity.AncientSpellJpaEntity.fromDomain(spell));
    }
}
