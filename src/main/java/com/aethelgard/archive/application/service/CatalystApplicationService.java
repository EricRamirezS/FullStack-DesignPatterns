package com.aethelgard.archive.application.service;

import com.aethelgard.archive.challenge.factory.CatalystFactory;
import com.aethelgard.archive.challenge.factory.ICatalyst;
import com.aethelgard.archive.domain.port.in.InvokeCatalystUseCase;
import org.springframework.stereotype.Service;

@Service
public class CatalystApplicationService implements InvokeCatalystUseCase {
    private final CatalystFactory catalystFactory;

    public CatalystApplicationService(CatalystFactory catalystFactory) {
        this.catalystFactory = catalystFactory;
    }

    @Override
    public String invoke(String type) {
        ICatalyst catalyst = catalystFactory.createCatalyst(type);
        return catalyst.getElement() + " catalyst invoked with power: " + catalyst.getPowerMultiplier();
    }
}
