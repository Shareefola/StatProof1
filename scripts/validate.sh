#!/usr/bin/env bash
set -euo pipefail

./gradlew ktlintCheck detekt test lint assembleRelease
