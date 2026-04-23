package com.aethelgard.archive;

import com.aethelgard.archive.challenge.adapter.LegacyRunicScroll;
import com.aethelgard.archive.challenge.factory.CatalystFactory;
import com.aethelgard.archive.challenge.factory.FireCatalyst;
import com.aethelgard.archive.challenge.factory.FrostCatalyst;
import com.aethelgard.archive.challenge.factory.VoidCatalyst;
import com.aethelgard.archive.challenge.singleton.ArcaneOracle;
import com.aethelgard.archive.domain.model.MagicalArtifact;
import com.aethelgard.archive.infrastructure.adapter.LegacyScrollAdapter;
import com.aethelgard.archive.infrastructure.persistence.adapter.ArtifactRepositoryAdapter;
import com.aethelgard.archive.presentation.rest.dto.ModernSpellDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ValidationTests {

    @Autowired
    private ArtifactRepositoryAdapter artifactAdapter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test1_ReadOnlyDatabaseValidation() {
        MagicalArtifact newArtifact = new MagicalArtifact(null, "Test Artifact", "Should not be saved");
        DataAccessException thrown = assertThrows(DataAccessException.class, () -> {
            artifactAdapter.save(newArtifact);
        }, "La BD debería ser READ-ONLY. Revisa los permisos del usuario librarian_readonly.");
        
        assertNotNull(thrown.getMessage());
    }

    @Test
    void test2_SingletonByReflection() throws Exception {
        Constructor<ArcaneOracle> constructor = ArcaneOracle.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()), 
            "¡Sacrilegio! Has expuesto el núcleo del Oráculo haciéndolo público. Debe ser PRIVATE.");
        
        ArcaneOracle instance1 = ArcaneOracle.getInstance();
        ArcaneOracle instance2 = ArcaneOracle.getInstance();
        assertSame(instance1, instance2, "getInstance() no retorna la misma instancia");

        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Callable<ArcaneOracle>> tasks = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            tasks.add(ArcaneOracle::getInstance);
        }

        List<Future<ArcaneOracle>> futures = executorService.invokeAll(tasks);
        for (Future<ArcaneOracle> future : futures) {
            assertSame(instance1, future.get(), "Falló el thread-safety del Singleton.");
        }
        executorService.shutdown();
    }

    @Autowired
    private com.aethelgard.archive.application.service.SpellApplicationService spellService;
    
    @Autowired
    private com.aethelgard.archive.challenge.observer.SecuritySentinel sentinel;

    @Test
    void test3_ObserverLogFile() throws Exception {
        // Registrar el centinela en el servicio para que el patrón Observer sea real
        spellService.addObserver(sentinel);

        mockMvc.perform(get("/api/spells/forbidden?minLevel=1")
                .remoteAddress("192.168.1.99"))
                .andExpect(status().isOk());

        String filename = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".log";
        Path logPath = Path.of(System.getProperty("user.dir"), filename);

        assertTrue(Files.exists(logPath), "El Centinela no escribió el log. Verifica SecuritySentinel.java.");

        List<String> lines = Files.readAllLines(logPath);
        assertFalse(lines.isEmpty(), "El archivo de log está vacío.");

        String lastLine = lines.get(lines.size() - 1);
        Pattern pattern = Pattern.compile("\\[\\d{2}:\\d{2}:\\d{2}\\] \\| IP: .+ \\| ACCESO PROHIBIDO: .+ \\| RIESGO: \\d+");
        assertTrue(pattern.matcher(lastLine).matches(), 
            "Formato de log inválido. Revisa el formato exacto en el TODO. Se obtuvo: " + lastLine);
    }

    @Test
    void test4_Factory() {
        CatalystFactory catalystFactory = new CatalystFactory();
        assertTrue(catalystFactory.createCatalyst("FIRE") instanceof FireCatalyst);
        assertTrue(catalystFactory.createCatalyst("FROST") instanceof FrostCatalyst);
        assertTrue(catalystFactory.createCatalyst("VOID") instanceof VoidCatalyst);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            catalystFactory.createCatalyst("UNKNOWN");
        });
        assertTrue(thrown.getMessage().contains("UNKNOWN"), "El mensaje de error debe contener el tipo desconocido");
    }

    @Test
    void test5_Adapter() {
        LegacyRunicScroll mockScroll = Mockito.mock(LegacyRunicScroll.class);
        // El formato real que devuelve el sistema legado después de nuestra mejora:
        Mockito.when(mockScroll.readLegacyData()).thenReturn("ID:0001#MAGO:Aethelgard#SPELL:Destello Arcano#MANA:25");

        try {
            // El estudiante debe implementar el constructor: public LegacyScrollAdapter(LegacyRunicScroll legacyRunicScroll)
            LegacyScrollAdapter studentAdapter = LegacyScrollAdapter.class.getConstructor(LegacyRunicScroll.class).newInstance(mockScroll);
            ModernSpellDTO result = studentAdapter.readSpell();
            
            assertNotNull(result, "MISION FALLIDA: El adaptador devolvió un objeto nulo.");
            assertEquals("Destello Arcano", result.getName(), "ERROR DE TRADUCCION: El nombre del hechizo no coincide con la trama rúnica.");
            assertFalse(result.getName().contains("#"), "REMANENTE RUNICO: El nombre extraído aún contiene caracteres de control (#).");
        } catch(NoSuchMethodException ex) {
            fail("ERROR ARQUITECTURAL: No se encontró un constructor que acepte LegacyRunicScroll. ¡La inyección es vital!");
        } catch(Exception e) {
            fail("FALLO CRITICO: Error al ejecutar el adaptador: " + e.getMessage());
        }
    }
}
