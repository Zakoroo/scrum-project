#!/usr/bin/env bash
set -euo pipefail

if command -v gradle >/dev/null 2>&1; then
  gradle wrapper --gradle-version 8.9
else
  echo "Gradle not found. Install Gradle or run from IDE to generate wrapper." >&2
fi
