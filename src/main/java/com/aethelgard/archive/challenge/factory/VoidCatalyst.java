package com.aethelgard.archive.challenge.factory;

public class VoidCatalyst implements ICatalyst {
    @Override
    public String getElement() { return "VOID"; }

    @Override
    public int getPowerMultiplier() { return 5; }
}
