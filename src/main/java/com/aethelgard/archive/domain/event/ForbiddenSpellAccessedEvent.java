package com.aethelgard.archive.domain.event;

public class ForbiddenSpellAccessedEvent {
    private final String spellName;
    private final int dangerLevel;
    private final String ipAddress;

    public ForbiddenSpellAccessedEvent(String spellName, int dangerLevel, String ipAddress) {
        this.spellName = spellName;
        this.dangerLevel = dangerLevel;
        this.ipAddress = ipAddress;
    }

    public String getSpellName() { return spellName; }
    public int getDangerLevel() { return dangerLevel; }
    public String getIpAddress() { return ipAddress; }
}
