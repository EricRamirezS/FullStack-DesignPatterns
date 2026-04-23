package com.aethelgard.archive.domain.port.out;

import com.aethelgard.archive.domain.model.AncientSpell;
import java.util.List;

public interface SpellRepositoryPort {
    List<AncientSpell> findByDangerLevelGreaterThan(int dangerLevel);
}
