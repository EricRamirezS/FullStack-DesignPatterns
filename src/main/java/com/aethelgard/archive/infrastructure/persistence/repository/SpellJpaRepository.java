package com.aethelgard.archive.infrastructure.persistence.repository;

import com.aethelgard.archive.infrastructure.persistence.entity.AncientSpellJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpellJpaRepository extends JpaRepository<AncientSpellJpaEntity, Long> {
//TODO: MISION_ESCRIBA - Patrón Repository (Spring Data JPA)
// 1. Extiende JpaRepository<AncientSpellJpaEntity, Long>.
// 2. Declara el método: List<AncientSpellJpaEntity> findByDangerLevelGreaterThan(int level);
// 3. Añade un @Query con JOIN FETCH sobre el campo `artifact` para prevenir el problema N+1.
// Nota: Solo operaciones SELECT. No añadir métodos de escritura.
}
