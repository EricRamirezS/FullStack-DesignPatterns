package com.aethelgard.archive.reference.adapter;

public class OldWandAdapter {
    // OldWand returns raw mana strings, modern services need structured power levels
    public int adapt(String rawWandOutput) {
        if(rawWandOutput != null && rawWandOutput.contains("MANA_LEVEL=")) {
            String val = rawWandOutput.replace("MANA_LEVEL=", "").trim();
            return Integer.parseInt(val);
        }
        return 0;
    }
}
