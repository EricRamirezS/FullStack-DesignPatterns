package com.aethelgard.archive.presentation.rest;

import com.aethelgard.archive.challenge.singleton.ArcaneOracle;
import com.aethelgard.archive.domain.model.AncientSpell;
import com.aethelgard.archive.domain.port.in.FindSpellsUseCase;
import com.aethelgard.archive.domain.port.in.InvokeCatalystUseCase;
import com.aethelgard.archive.domain.port.in.ReadScrollUseCase;
import com.aethelgard.archive.presentation.rest.dto.GrimoireResponse;
import com.aethelgard.archive.presentation.rest.dto.ModernSpellDTO;
import com.aethelgard.archive.presentation.rest.mapper.SpellDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador principal de la Biblioteca de Aethelgard.
 * Actúa como el portal de entrada para interactuar con los artefactos y hechizos antiguos.
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Grimorio de Aethelgard", description = "Portales de acceso místico a la Biblioteca Etérea")
public class ArchiveController {

    private final FindSpellsUseCase findSpellsUseCase;
    private final InvokeCatalystUseCase invokeCatalystUseCase;
    private final ReadScrollUseCase readScrollUseCase;
    private final SpellDtoMapper spellDtoMapper;

    public ArchiveController(FindSpellsUseCase findSpellsUseCase,
                             InvokeCatalystUseCase invokeCatalystUseCase,
                             ReadScrollUseCase readScrollUseCase,
                             SpellDtoMapper spellDtoMapper) {
        this.findSpellsUseCase = findSpellsUseCase;
        this.invokeCatalystUseCase = invokeCatalystUseCase;
        this.readScrollUseCase = readScrollUseCase;
        this.spellDtoMapper = spellDtoMapper;
    }

    /**
     * Forja un catalizador mágico basado en un elemento específico.
     * Retorna el resultado de la invocación arcana.
     *
     * @param type El elemento místico (ej. fuego, hielo, vacío).
     * @return El resultado de la invocación.
     */
    @PostMapping("/catalysts/{type}")
    @Operation(summary = "Forja un catalizador mágico según su elemento", 
               description = "Inicia un ritual para invocar un catalizador místico basado en el elemento proporcionado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catalizador forjado con éxito",
                     content = @Content(schema = @Schema(implementation = GrimoireResponse.class))),
        @ApiResponse(responseCode = "400", description = "Elemento místico desconocido o inválido")
    })
    public ResponseEntity<GrimoireResponse> invokeCatalyst(@PathVariable String type) {
        return ResponseEntity.ok(new GrimoireResponse(invokeCatalystUseCase.invoke(type)));
    }

    /**
     * Consulta el Grimorio para obtener una lista de hechizos prohibidos,
     * filtrados por un nivel mínimo de poder y la IP del solicitante.
     *
     * @param minLevel Nivel mínimo de peligro del hechizo.
     * @param request La petición HTTP para rastrear el origen de la consulta.
     * @return Lista de hechizos descifrados.
     */
    @GetMapping("/spells/forbidden")
    @Operation(summary = "Consulta el Grimorio Prohibido", 
               description = "Recupera los hechizos restringidos de la biblioteca que superan el nivel de peligro especificado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hechizos prohibidos recuperados con éxito",
                     content = @Content(schema = @Schema(implementation = ModernSpellDTO.class))),
        @ApiResponse(responseCode = "403", description = "Acceso denegado por los Centinelas de Seguridad")
    })
    public ResponseEntity<List<ModernSpellDTO>> getForbiddenSpells(@RequestParam(defaultValue = "5") int minLevel, HttpServletRequest request) {
        List<AncientSpell> spells = findSpellsUseCase.findForbiddenSpells(minLevel, request.getRemoteAddr());
        return ResponseEntity.ok(spellDtoMapper.toDtoList(spells));
    }

    /**
     * Descifra y lee un pergamino heredado (legacy) del antiguo sistema mágico.
     *
     * @return El contenido traducido del pergamino antiguo.
     */
    @GetMapping("/scrolls/legacy")
    @Operation(summary = "Descifra un pergamino del sistema arcano antiguo", 
               description = "Utiliza el Adaptador de Runas para traducir el conocimiento de un pergamino antiguo al sistema moderno.")
    @ApiResponse(responseCode = "200", description = "Pergamino descifrado correctamente",
                 content = @Content(schema = @Schema(implementation = GrimoireResponse.class)))
    public ResponseEntity<GrimoireResponse> readLegacyScroll() {
        return ResponseEntity.ok(new GrimoireResponse(readScrollUseCase.readLegacyScroll()));
    }

    /**
     * Verifica el estado del Oráculo Arcano para determinar si se encuentra
     * alineado e instanciado correctamente en memoria.
     *
     * @return El estado místico del Oráculo.
     */
    @GetMapping("/oracle/status")
    @Operation(summary = "Verifica si el Oráculo Único está activo", 
               description = "Consulta la instancia única del Oráculo Arcano para confirmar su alineación con este plano.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado del Oráculo obtenido correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno conectando con el plano astral del Oráculo")
    })
    public ResponseEntity<GrimoireResponse> getOracleStatus() {
        try {
            ArcaneOracle instance1 = ArcaneOracle.getInstance();
            if (instance1 != null) {
                return ResponseEntity.ok(new GrimoireResponse("El Oráculo está ALINEADO."));
            } else {
                return ResponseEntity.ok(new GrimoireResponse("El Oráculo no ha sido instanciado correctamente."));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new GrimoireResponse("Error conectando con el Oráculo: " + e.getMessage()));
        }
    }
}
