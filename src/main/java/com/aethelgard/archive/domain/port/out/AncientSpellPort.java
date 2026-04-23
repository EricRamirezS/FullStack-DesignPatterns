package com.aethelgard.archive.domain.port.out;

import com.aethelgard.archive.domain.model.AncientSpell;
import java.util.List;

/**
 * Puerto de Salida: El Portal de Hechizos Antiguos.
 * Define el contrato para acceder a los registros sagrados de hechizos en la persistencia.
 */
public interface AncientSpellPort {
    
    /**
     * Recupera todos los hechizos antiguos custodiados en el archivo.
     * @return Lista de todos los hechizos antiguos encontrados.
     */
    List<AncientSpell> findAll();
}
