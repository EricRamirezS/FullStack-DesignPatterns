package com.aethelgard.archive.reference.singleton;

public class ManaGridConfig {
    private static volatile ManaGridConfig instance;

    private ManaGridConfig() {
        // Constructor privado para prevenir instanciación
        if(instance != null){
            throw new IllegalStateException("¡Sacrilegio! Has expuesto el núcleo.");
        }
    }

    public static ManaGridConfig getInstance() {
        if (instance == null) {
            synchronized (ManaGridConfig.class) {
                if (instance == null) {
                    instance = new ManaGridConfig();
                }
            }
        }
        return instance;
    }
}
