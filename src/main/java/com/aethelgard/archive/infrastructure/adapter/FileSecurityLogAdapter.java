package com.aethelgard.archive.infrastructure.adapter;

import com.aethelgard.archive.domain.port.out.SecurityLogPort;
import org.springframework.stereotype.Component;

/**
 * Adaptador de infraestructura para el registro de seguridad en archivos.
 * Implementa el puerto de salida SecurityLogPort.
 */
@Component
public class FileSecurityLogAdapter implements SecurityLogPort {

    /**
     * Registra un acceso no autorizado en el sistema de archivos.
     * 
     * @param spellName Nombre del hechizo accedido ilegalmente.
     * @param dangerLevel Nivel de peligro detectado.
     * @param ipAddress Dirección IP de origen.
     */
    @Override
    public void logUnauthorizedAccess(String spellName, int dangerLevel, String ipAddress) {
        // La implementación real se delega comúnmente al SecuritySentinel (Observer).
        // Este adaptador sirve como punto de extensión místico.
    }
}
