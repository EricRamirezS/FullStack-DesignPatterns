package com.aethelgard.archive.challenge.factory;

public class FireCatalyst implements ICatalyst {
    @Override
    public String getElement() { return "FIRE"; }

    @Override
    public int getPowerMultiplier() { return 2; }
}
