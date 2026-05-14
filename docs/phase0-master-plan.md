# StatProof Phase 0 Master Plan

## Full repository architecture
- Multi-module Gradle repository: `app`, `core`, `domain`, `data`, `proofengine`, `examples`, `tests`.
- Offline resources and theorem catalogs in `examples/src/main/resources`.
- Deterministic proof generation and validation centered in `proofengine`.

## Package structure
- `com.statproof.core`
- `com.statproof.domain.model`
- `com.statproof.data`
- `com.statproof.proofengine.{ast,parser,rules,engine,verification}`
- `com.statproof` (app UI + nav)

## Module dependency graph
- `app -> data, domain, proofengine, core`
- `data -> core, domain, proofengine`
- `proofengine -> domain`
- `tests -> proofengine`

## Data flow diagram
1. User input (structured/NL/math) enters app.
2. App sends input to data/repository.
3. Repository invokes proof engine parser + transformation pipeline.
4. Engine returns deterministic proof steps + verification.
5. UI renders steps + LaTeX offline.

## Proof engine architecture
- AST expression nodes.
- Deterministic parser.
- Rewrite registry.
- Recursive simplification engine.
- Equivalence checker.
- Verification output model.

## Rendering pipeline architecture
- Expression AST -> LaTeX rendering -> UI renderer.
- Caching keyed by expression hash.

## State management architecture
- MVVM on top of clean domain boundaries.
- Immutable domain models + deterministic transitions.

## CI/CD pipeline architecture
- GitHub Actions: setup JDK/Android, cache Gradle, run lint/tests/build, sign release APK, upload artifact.

## Testing strategy
- Parser tests
- Simplification/equivalence tests
- Canonical derivation tests
- UI smoke tests

## Security model
- No telemetry, no network dependency for runtime features.
- Keystore secrets only in CI secret store.

## Offline data architecture
- JSON theorem catalog and canonical proofs packaged in app resources.

## Technology decision matrix
| Decision | Choice | Reason |
|---|---|---|
| Architecture | Clean + MVVM | Separation + testability |
| DI | Koin | Simpler deterministic setup |
| Symbolic engine | Native Kotlin | Full offline control |
| Local storage | JSON resources | Deterministic and extensible |
| Math rendering | LaTeX pipeline | Standards-friendly |
| Navigation | Navigation Compose | Stable Android standard |
| Serialization | kotlinx.serialization | Fast, typed, deterministic |
| Search indexing | Local keyword index | Offline and reproducible |

## PHASE OUTPUT, CONTINUITY, AND DELIVERY RULES

Mandatory Incremental Repository Delivery

After completing EACH phase, the AI must:

1. Generate all required code, configuration, assets, documentation, and tests for that phase.
2. Preserve every valid file generated in all previous phases.
3. Carry forward the complete repository state into the next phase.
4. Never regenerate unrelated files unless modifications are necessary.
5. Maintain full repository continuity across all phases.

Each new phase must therefore represent:

Previous Repository State
+ New Files
+ Modified Files
+ Additional Features
= Updated Complete Repository

The repository must continuously evolve phase-by-phase until the final production-ready repository is complete.
