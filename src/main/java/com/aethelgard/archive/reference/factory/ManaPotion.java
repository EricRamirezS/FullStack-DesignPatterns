package com.aethelgard.archive.reference.factory;

public class ManaPotion implements IPotion {
    @Override
    public String getType() { return "MANA"; }
    @Override
    public int getRestoreAmount() { return 100; }
}
