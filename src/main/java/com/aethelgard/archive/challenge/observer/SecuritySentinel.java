package com.aethelgard.archive.challenge.observer;

import org.springframework.stereotype.Component;

/**
 * Desafío: El Centinela de Seguridad.
 * Este componente observa el acceso a hechizos prohibidos y registra cualquier intrusión.
 */
@Component
public class SecuritySentinel implements SpellAccessObserver {
//TODO: MISION_ESCRIBA - Patrón Observer (Log de Seguridad)
// Implementa SpellAccessObserver. Método: void onForbiddenAccess(String spellName, int dangerLevel, String ip)
// Se invoca automáticamente cuando danger_level > 5.
// 1. Nombre del archivo: LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".log"
//    Ruta: Path.of(System.getProperty("user.dir"), nombreArchivo)
// 2. Abre el archivo en modo APPEND (StandardOpenOption.APPEND, CREATE).
// 3. Escribe exactamente esta línea (con salto de línea \n al final):
//    [HH:mm:ss] | IP: {ip} | ACCESO PROHIBIDO: {spellName} | RIESGO: {dangerLevel}
//    donde HH:mm:ss = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
// La IP proviene de HttpServletRequest.getRemoteAddr() en ArchiveController.

    /**
     * Reacciona ante un acceso prohibido registrando el evento en un pergamino de log.
     * 
     * @param spellName Nombre del hechizo accedido.
     * @param dangerLevel Nivel de peligro del hechizo.
     * @param ip Dirección IP del intruso.
     */
    @Override
    public void onForbiddenAccess(String spellName, int dangerLevel, String ip) {
        throw new UnsupportedOperationException("//TODO: MISION_ESCRIBA");
    }
}
