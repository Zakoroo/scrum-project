#!/usr/bin/env bash
set -euo pipefail

HOOK=.git/hooks/pre-commit
cat > "$HOOK" <<'EOF'
#!/usr/bin/env bash
# Run quick quality gates before committing
./gradlew -q check || {
  echo
  echo "[pre-commit] Gradle checks failed. Fix issues, then commit again."
  exit 1
}
EOF
chmod +x "$HOOK"
echo "[ok] pre-commit hook installed."