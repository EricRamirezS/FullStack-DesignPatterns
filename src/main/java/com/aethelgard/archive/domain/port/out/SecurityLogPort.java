package com.aethelgard.archive.domain.port.out;

public interface SecurityLogPort {
    void logUnauthorizedAccess(String spellName, int dangerLevel, String ipAddress);
}
