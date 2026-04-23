package com.aethelgard.archive.presentation.rest;

import com.aethelgard.archive.infrastructure.persistence.entity.MagicalArtifactJpaEntity;
import com.aethelgard.archive.reference.adapter.OldWandAdapter;
import com.aethelgard.archive.reference.factory.IPotion;
import com.aethelgard.archive.reference.factory.PotionFactory;
import com.aethelgard.archive.reference.observer.WeatherWatcher;
import com.aethelgard.archive.reference.repository.ArtifactReferenceRepository;
import com.aethelgard.archive.reference.singleton.ManaGridConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;
import com.aethelgard.archive.presentation.rest.dto.reference.*;
import java.util.List;
import com.aethelgard.archive.reference.factory.PotionType;

/**
 * Controlador de Referencia para los Patrones de Diseño.
 * Contiene implementaciones de ejemplo curadas por los Grandes Magos.
 */
@RestController
@RequestMapping(value = "/api/reference", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Ejemplos de Patrones (Reference)", description = "Endpoints para probar las implementaciones de referencia de los patrones")
public class ReferenceController {

    private final ArtifactReferenceRepository repository;

    public ReferenceController(ArtifactReferenceRepository repository) {
        this.repository = repository;
    }

    /**
     * Prueba el patrón Adapter adaptando un valor de la varita mágica antigua
     * a un formato moderno compatible con la red de maná.
     *
     * @param rawWandOutput El valor en bruto de la varita (ej. MANA_LEVEL=9000).
     * @return Respuesta con el valor adaptado en formato JSON.
     */
    @GetMapping(value = "/adapter", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Probar el patrón Adapter", 
               description = "Adapta la salida incompatible de una varita antigua al estándar de la red de maná moderna.")
    @ApiResponse(responseCode = "200", description = "Valor adaptado con éxito",
                 content = @Content(schema = @Schema(implementation = AdapterResponse.class)))
    public ResponseEntity<AdapterResponse> testAdapter(
            @RequestParam(defaultValue = "MANA_LEVEL=9000") String rawWandOutput) {
        OldWandAdapter adapter = new OldWandAdapter();
        int adaptedValue = adapter.adapt(rawWandOutput);
        return ResponseEntity.ok(new AdapterResponse("Valor adaptado", adaptedValue));
    }

    /**
     * Prueba el patrón Factory creando pociones mágicas de diferentes tipos.
     * Retorna detalles de la poción generada por la fábrica.
     *
     * @param type El tipo de poción a generar (HEALTH o MANA).
     * @return Respuesta con los detalles de la poción en formato JSON.
     */
    @GetMapping(value = "/factory", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Probar el patrón Factory", 
               description = "Utiliza una fábrica de pociones para instanciar el tipo de elixir místico solicitado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Poción creada con éxito",
                     content = @Content(schema = @Schema(implementation = FactoryResponse.class))),
        @ApiResponse(responseCode = "400", description = "Tipo de poción no soportado por la alquimia actual")
    })
    public ResponseEntity<FactoryResponse> testFactory(@RequestParam(defaultValue = "HEALTH") PotionType type) {
        PotionFactory factory = new PotionFactory();
        try {
            IPotion potion = factory.createPotion(type);
            return ResponseEntity.ok(new FactoryResponse("Poción creada correctamente", potion.getClass().getSimpleName(), potion));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new FactoryResponse(e.getMessage(), null, null));
        }
    }

    /**
     * Prueba el patrón Singleton obteniendo la instancia única de configuración
     * de la red de maná y verificando si ambas referencias apuntan al mismo espacio en memoria.
     *
     * @return Respuesta con el resultado de la comparación de instancias en formato JSON.
     */
    @GetMapping(value = "/singleton", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Probar el patrón Singleton", 
               description = "Verifica que solo exista una única fuente de verdad para la configuración de la red de maná.")
    @ApiResponse(responseCode = "200", description = "Instancia única verificada",
                 content = @Content(schema = @Schema(implementation = SingletonResponse.class)))
    public ResponseEntity<SingletonResponse> testSingleton() {
        ManaGridConfig instance1 = ManaGridConfig.getInstance();
        ManaGridConfig instance2 = ManaGridConfig.getInstance();
        boolean isSame = (instance1 == instance2);
        return ResponseEntity.ok(new SingletonResponse("Instancias obtenidas", isSame));
    }

    /**
     * Prueba el patrón Observer disparando un evento de tormenta de maná.
     * Los observadores registrados reaccionarán ante esta intensidad.
     *
     * @param intensity La intensidad de la tormenta de maná.
     * @return Respuesta indicando que el evento ha sido procesado en formato JSON.
     */
    @GetMapping(value = "/observer", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Probar el patrón Observer", 
               description = "Dispara una alerta climática mística para que todos los observadores registrados reaccionen.")
    @ApiResponse(responseCode = "200", description = "Evento de observación disparado",
                 content = @Content(schema = @Schema(implementation = ObserverResponse.class)))
    public ResponseEntity<ObserverResponse> testObserver(@RequestParam(defaultValue = "5") int intensity) {
        WeatherWatcher watcher = new WeatherWatcher();
        watcher.onManaStorm(intensity);
        return ResponseEntity.ok(new ObserverResponse("Tormenta simulada", intensity, "Revisa los logs de la consola"));
    }

    /**
     * Prueba el patrón Repository realizando búsquedas de artefactos mágicos
     * por nombre usando la base de datos a través de Spring Data JPA.
     *
     * @param name Nombre parcial del artefacto a buscar.
     * @return Lista de entidades de artefactos en formato JSON.
     */
    @GetMapping(value = "/repository", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Probar el patrón Repository", 
               description = "Accede a las bóvedas de datos (Base de Datos) para recuperar artefactos registrados.")
    @ApiResponse(responseCode = "200", description = "Artefactos encontrados",
                 content = @Content(schema = @Schema(implementation = MagicalArtifactJpaEntity.class)))
    public ResponseEntity<List<MagicalArtifactJpaEntity>> testRepository(@RequestParam(defaultValue = "") String name) {
        List<MagicalArtifactJpaEntity> artifacts = repository.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(artifacts);
    }
}

