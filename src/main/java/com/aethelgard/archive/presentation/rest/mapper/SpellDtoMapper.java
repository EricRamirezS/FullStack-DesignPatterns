package com.aethelgard.archive.presentation.rest.mapper;

import com.aethelgard.archive.domain.model.AncientSpell;
import com.aethelgard.archive.presentation.rest.dto.ModernSpellDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper místico encargado de transformar modelos de dominio de hechizos antiguos
 * en representaciones modernas (DTOs) compatibles con la Biblioteca.
 */
@Component
public class SpellDtoMapper {

    /**
     * Transforma un hechizo antiguo en su versión moderna.
     * @param spell Hechizo antiguo del dominio.
     * @return DTO del hechizo moderno.
     */
    public ModernSpellDTO toDto(AncientSpell spell) {
        return new ModernSpellDTO(spell.getName());
    }

    /**
     * Transforma una lista de hechizos antiguos en una lista de DTOs modernos.
     * @param spells Lista de modelos de dominio.
     * @return Lista de DTOs.
     */
    public List<ModernSpellDTO> toDtoList(List<AncientSpell> spells) {
        return spells.stream().map(this::toDto).collect(Collectors.toList());
    }
}
