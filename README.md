# Aethelgard-Archive-Core

*"La Biblioteca Etérea de Aethelgard ha sido vulnerada. Como Escriba de Código, tu misión es restaurar los pilares mágicos (Patrones de Diseño) antes de que el conocimiento se pierda en el Vacío."*

Cuentas antiguas narran cómo los Grandes Magos de antaño forjaron barreras lógicas irreductibles para proteger los secretos de la magia. Sin embargo, fracturas recientes en el Código Fuente han debilitado las runas, exponiendo artefactos de poder y hechizos prohibidos a los saqueadores del averno.

Tu objetivo como aprendiz y Escriba de la Orden es reparar el entramado místico completando los rituales incompletos marcados a lo largo del repositorio. El Concilio confiará en que aplicarás sabiamente los patrones de diseño en armonía con nuestra Arquitectura Hexagonal sagrada.

## Arquitectura Hexagonal

```text
com.aethelgard.archive
├── domain           # Modelos puros, Puertos e Interfaces (Sin dependencias externas)
├── application      # Casos de uso (Orquestadores)
├── infrastructure   # Adaptadores, Spring Data JPA, Persistencia
├── presentation     # Controladores REST, DTOs, Mappers
├── challenge        # Componentes ROTOS esperando tu reparación
└── reference        # Código de guía completado por los Magos Supremos
```

## Instrucciones para el Escriba

1. Haz **FORK** de este repositorio y clónalo en tu estación de trabajo local.
2. Crea el archivo `.env` en la raíz. Las credenciales reales de la base de datos de producción serán entregadas por el Concilio a través del AVA.
3. Estudia con detenimiento el paquete `reference`. Los Maestros han dejado implementaciones 100% funcionales (Singleton, Factory, Repository, Observer, Adapter) para que guíes tus pasos.
4. Explora el código en busca de la marca sagrada `//TODO: MISION_ESCRIBA`. Allí encontrarás las directrices de lo que debes forjar para reparar cada clase del paquete `challenge` y `infrastructure`.
5. Ejecuta continuamente `./mvnw test`. El **Concilio de Magos** (la suite de tests JUnit) verificará cada uno de tus cambios. Al iniciar, todos los tests fallarán (color rojo). Tu misión termina cuando observes todo en color verde.

> ⚠️ ADVERTENCIA CRÍTICA:
> La base de datos se encuentra bajo un encantamiento de **SOLO LECTURA**. Cualquier intento de escritura o modificación de los registros de Aethelgard resultará en una fatídica `DataAccessException` que consumirá tus puntos de magia. No lo intentes.

## El Grimorio de Patrones a Restaurar

| Patrón                 | Clase Desafío                           | Clase Referencia (Guía)                | Prueba Validadora (Tests)               |
|------------------------|-----------------------------------------|----------------------------------------|-----------------------------------------|
| Singleton              | `ArcaneOracle`                          | `ManaGridConfig`                       | Test 2 — Singleton por Reflection       |
| Factory Method         | `CatalystFactory`                       | `PotionFactory`                        | Test 4 — Factory                        |
| Repository (JPA)       | `SpellJpaRepository`                    | `ArtifactReferenceRepository`          | Test 1 — Validación de BD Read-Only     |
| Adapter                | `LegacyScrollAdapter`                   | `OldWandAdapter`                       | Test 5 — Adapter                        |
| Observer + File I/O    | `SecuritySentinel`                      | `WeatherWatcher`                       | Test 3 — Archivo de log del Observer    |

La suerte de Aethelgard reposa en tus manos. _¡Escribe con audacia!_
