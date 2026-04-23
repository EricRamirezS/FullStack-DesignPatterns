package com.aethelgard.archive.reference.factory;

public class HealthPotion implements IPotion {
    @Override
    public String getType() { return "HEALTH"; }
    @Override
    public int getRestoreAmount() { return 50; }
}
