# StatProof

StatProof is an offline-first Android app for deterministic, step-by-step statistical proof generation, verification, and exploration.

## Architecture choice summary
- **Architecture**: Clean Architecture + MVVM presentation.
- **DI**: Koin (lightweight, deterministic wiring, no annotation processor).
- **Symbolic engine**: Native Kotlin symbolic engine (`proofengine` module).
- **Storage**: Local JSON resources, deterministic parsing (`kotlinx.serialization`).
- **Math rendering**: Offline LaTeX output pipeline + renderer integration point.
- **Navigation**: Jetpack Navigation Compose.
- **Serialization**: `kotlinx.serialization`.
- **Search indexing**: Offline keyword index seeded from theorem metadata.

## Modules
- `app`: Android app + Compose UI/navigation.
- `core`: shared deterministic utilities.
- `domain`: core proof domain models.
- `proofengine`: AST/parser/rewrite/simplification/equivalence/verification.
- `data`: offline repository and parsing.
- `examples`: offline theorem/proof datasets.
- `tests`: canonical cross-module verification tests.
- `docs`: architecture + extension docs.
- `scripts`: reproducible local CI helper scripts.

## Build
```bash
./gradlew test
./gradlew lint
./gradlew assembleRelease
```

## GitHub Actions signing secrets
- `ANDROID_KEYSTORE_BASE64`
- `ANDROID_KEYSTORE_PASSWORD`
- `ANDROID_KEY_ALIAS`
- `ANDROID_KEY_PASSWORD`

The workflow decodes the keystore and sets `ANDROID_KEYSTORE_PATH` for release signing.

## Testing
- Unit tests in `proofengine` and `tests` modules validate deterministic symbolic behavior.

## UI screenshot
![StatProof Home Placeholder](docs/screenshots/ui-home-placeholder.png)

## Contributing
1. Keep changes deterministic and offline.
2. Add tests for rule or parser changes.
3. Keep theorem datasets extensible JSON-first.
