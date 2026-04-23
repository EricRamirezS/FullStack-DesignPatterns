package com.aethelgard.archive.challenge.observer;

public interface SpellAccessObserver {
    void onForbiddenAccess(String spellName, int dangerLevel, String ip);
}
