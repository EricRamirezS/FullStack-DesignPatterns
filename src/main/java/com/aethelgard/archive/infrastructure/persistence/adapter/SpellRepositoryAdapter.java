package com.aethelgard.archive.infrastructure.persistence.adapter;

import com.aethelgard.archive.domain.model.AncientSpell;
import com.aethelgard.archive.domain.port.out.SpellRepositoryPort;
import com.aethelgard.archive.infrastructure.persistence.repository.SpellJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpellRepositoryAdapter implements SpellRepositoryPort {

    private final SpellJpaRepository spellJpaRepository;

    public SpellRepositoryAdapter(SpellJpaRepository spellJpaRepository) {
        this.spellJpaRepository = spellJpaRepository;
    }

    @Override
    public List<AncientSpell> findByDangerLevelGreaterThan(int dangerLevel) {
        throw new UnsupportedOperationException("Requiere solucionar el Challenge C en SpellJpaRepository. Descomenta el código de abajo cuando esté listo.");
        /*
        return spellJpaRepository.findByDangerLevelGreaterThan(dangerLevel).stream()
                .map(entity -> entity.toDomain())
                .collect(Collectors.toList());
        */
    }

    // Required for the Read-Only test
    public void save(AncientSpell spell) {
        spellJpaRepository.save(com.aethelgard.archive.infrastructure.persistence.entity.AncientSpellJpaEntity.fromDomain(spell));
    }
}
