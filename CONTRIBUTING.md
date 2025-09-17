# Contributing Guide

## Branching & PRs

-  Use short-lived feature branches (e.g., `feat/auth-login`), open a PR into `main`.
-  Pairing is encouraged—still open a PR so the work is visible.

## Commit Style (Conventional Commits)

Examples: `feat: add login`, `fix: handle 401`, `docs: update README`, `refactor: split service`, `test: add reducer tests`, `chore: bump deps`.

## PR Checklist

-  Link the issue (e.g., `Closes #123`).
-  Tests updated/added.
-  Docs updated (README/inline/migration notes).
-  CI green (formatter, linter, tests, static analysis).
-  No secrets/keys committed.

## Review Etiquette

-  Be kind, specific, actionable. Ask questions, don’t demand.
-  Authors respond to all comments; resolve threads or note follow-ups.

## Approvals

-  1 reviewer for routine code; 2 for risky areas (security, DB schema, public APIs).
-  Use CODEOWNERS for critical paths; authors cannot self-approve.

## Definition of Done (DoD)

-  ✅ CI green
-  ✅ Approved PR (per rules above)
-  ✅ Docs updated
-  ✅ Issue linked (e.g., `Closes #123`)
-  ✅ Secrets handled via env/secret manager
