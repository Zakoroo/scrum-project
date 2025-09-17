#!/usr/bin/env bash
set -euo pipefail

# Always call through your cross-platform Docker MVN runner
ROOT_DIR="$(cd -- "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
RUN_MVN="$ROOT_DIR/dev/run-mvn.sh"

# Default flags (quiet-ish), override with MVN_FLAGS or QUIET=1
MVN_FLAGS=${MVN_FLAGS:--ntp -B}
if [[ "${QUIET:-0}" == "1" ]]; then MVN_FLAGS="$MVN_FLAGS -q"; fi

cmd="${1:-help}"
shift || true

run() { "$RUN_MVN" $MVN_FLAGS "$@"; }

case "$cmd" in
  help|-h|--help)
    cat <<'EOF'
Project commands (aliases):

  build         → mvn clean verify
  test          → mvn test
  package       → mvn -DskipTests package
  clean         → mvn clean
  run           → mvn exec:java -Dexec.mainClass=com.ecologicstudios.App
  deps          → mvn -U dependency:resolve
  info          → mvn -Ddetail=true help:effective-pom
  fmt           → mvn spotless:apply            (if Spotless configured)
  checkstyle    → mvn checkstyle:check          (if Checkstyle configured)

Options:
  QUIET=1       Reduce logs further
  MVN_FLAGS="..."  Custom maven flags

Examples:
  dev/do.sh build
  QUIET=1 dev/do.sh test
  dev/do.sh run
EOF
    ;;
  build)        run clean verify ;;
  test)         run test ;;
  package)      run -DskipTests package ;;
  clean)        run clean ;;
  run)          run exec:java -Dexec.mainClass="${MAIN_CLASS:-com.ecologicstudios.App}" ;;
  deps)         run -U dependency:resolve ;;
  info)         run -Ddetail=true help:effective-pom ;;
  fmt)          run spotless:apply ;;
  checkstyle)   run checkstyle:check ;;
  *)
    echo "Unknown command: $cmd"
    echo "Try: dev/do.sh help"
    exit 2
    ;;
esac
