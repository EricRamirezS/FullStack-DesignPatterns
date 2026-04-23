package com.aethelgard.archive.domain.port.in;

import com.aethelgard.archive.domain.model.AncientSpell;

import java.util.List;

public interface FindSpellsUseCase {
    List<AncientSpell> findForbiddenSpells(int minDangerLevel, String requestIp);
}
