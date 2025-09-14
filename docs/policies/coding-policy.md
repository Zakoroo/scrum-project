# Coding Policy & Code Reviews (GitHub)

## Branching & PRs
- Use **short-lived feature branches**; merge via **Pull Requests (PRs)** into `main`/`trunk`.
- Pair programming is encouraged; still open a PR so the work is visible and documented.

## Review Turnaround – SLA
- Aim to **review within 24 hours (weekdays)**. If you need more time, acknowledge with an **ETA**.
- Use a `#reviews` channel: author posts the PR link + quick context and tags a reviewer.

## Approvals
- **1 reviewer** for routine code; **2 reviewers** for risky areas (security, DB schema, public APIs).
- The author **cannot self-approve**. Use **CODEOWNERS** to enforce extra reviewers for critical paths.

## Automated Gates (before humans look)
- CI must run **formatter, linter, unit tests, and static analysis**. Fail fast.

## Tests & Docs
- New/changed code includes **unit tests**; maintain/adjust existing tests.
- Update **inline docs** and **README** as needed; include **migration notes** when relevant.

## Commit Message Style
- Prefer **conventional commits** (`feat:`, `fix:`, `docs:`, `refactor:`, `test:`, `chore:`). Keep messages crisp and explain the **why**.

## Reviewer Rotation & Load
- Rotate a **primary reviewer weekly**; anyone may jump in.
- If a reviewer already has **≥ 2 pending** PRs, choose someone else to avoid bottlenecks.

## Review Etiquette
- Be kind, specific, and actionable. Ask questions, don’t demand.
- Authors respond to all comments; resolve threads or explain follow-ups.

## Definition of Done (DoD)
- ✅ CI green (formatter + linter + tests + static analysis)
- ✅ **Approved PR** (per rules above)
- ✅ **Docs updated** (class/method purpose, README/wiki if needed)
- ✅ **Issue/task linked** to the PR (use “Closes #123” in description)
- ✅ **No secrets/keys** in the repo; environment handled correctly

## Weekly Tech Huddle (15–30 min)
- Discuss **patterns, conventions, refactoring opportunities**—not line-by-line reviews.

## Hotfixes
- Urgent fixes may fast-track with **pair review** and a **retro PR** documenting follow-ups.

## Security & Data Hygiene
- Never commit secrets or credentials; use env vars and secret managers.
- Keep `.gitignore` current; scan dependencies regularly.

## Style & Tooling
- Enforce **auto-formatter**, **linter**, and minimal **editorconfig** via CI in GitHub Actions.
