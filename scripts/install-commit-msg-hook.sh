#!/usr/bin/env bash
set -euo pipefail

mkdir -p .git/hooks
HOOK=.git/hooks/commit-msg
cat > "$HOOK" <<'EOF'
#!/usr/bin/env bash
# commit-msg hook to validate Conventional Commits
# Usage: Git passes the commit message file path as $1

MSG_FILE="$1"
FIRST_LINE="$(head -n1 "$MSG_FILE" | tr -d '\r\n')"

# Allowed types
TYPES='build|chore|ci|docs|feat|fix|perf|refactor|revert|style|test'
# Regex: type(\(scope\))?!?: space then non-empty subject
REGEX="^(${TYPES})(\\([^\\)]+\\))?(!)?: .+"

if echo "$FIRST_LINE" | grep -Pq "$REGEX"; then
  exit 0
fi

cat <<EOM
[commit-msg] Invalid commit message format.
Expected: <type>(<optional-scope>)<optional-!>: <subject>
Types: ${TYPES}
Examples:
feat(ui): add dark mode toggle
fix(auth): handle token refresh on 401
refactor(core)!: remove deprecated config

Your message was:
"$FIRST_LINE"
EOM
exit 1
EOF

chmod +x "$HOOK"
echo "[ok] commit-msg hook installed."