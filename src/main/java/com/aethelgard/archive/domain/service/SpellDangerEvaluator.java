package com.aethelgard.archive.domain.service;

import com.aethelgard.archive.domain.event.ForbiddenSpellAccessedEvent;
import com.aethelgard.archive.domain.model.AncientSpell;
import com.aethelgard.archive.challenge.observer.SpellAccessObserver;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpellDangerEvaluator {
    private final List<SpellAccessObserver> observers = new ArrayList<>();

    public SpellDangerEvaluator(List<SpellAccessObserver> observers) {
        if (observers != null) {
            this.observers.addAll(observers);
        }
    }

    public void addObserver(SpellAccessObserver observer) {
        this.observers.add(observer);
    }

    public void evaluate(AncientSpell spell, String requestIp) {
        if (spell.getDangerLevel() > 5) {
            ForbiddenSpellAccessedEvent event = new ForbiddenSpellAccessedEvent(
                spell.getName(), spell.getDangerLevel(), requestIp
            );
            notifyObservers(event);
        }
    }

    private void notifyObservers(ForbiddenSpellAccessedEvent event) {
        for (SpellAccessObserver observer : observers) {
            observer.onForbiddenAccess(event.getSpellName(), event.getDangerLevel(), event.getIpAddress());
        }
    }
}
