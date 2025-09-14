# Agile Project Starter (SCRUM)

A ready-to-use GitHub repository template for Scrum-style agile projects. It ships with:

- Issue forms for **User Stories**, **Bugs**, **Tasks**, **Spikes**
- A PR template aligned with **Conventional Commits**
- **CI** (GitHub Actions) that runs formatter/linter/tests for Python and/or Node.js when detected
- **CODEOWNERS**, **branch protections** (configure in Settings), and a documented **Definition of Done**
- Docs for team working agreements and review etiquette

> **Scrum terms at a glance**
> - **Product Backlog Item (PBI)**: work item such as a story, bug, or spike.
> - **User Story**: end-user value slice: *As a [role], I want [capability], so that [benefit].*
> - **Epic**: large initiative grouping multiple stories.
> - **Sprint**: timebox (e.g., 1–2 weeks) where PBIs are delivered.
> - **Definition of Ready (DoR)**: checklist a story must meet before sprint commitment.
> - **Definition of Done (DoD)**: quality bar to consider work complete.

## Quick start

1. Create this repo (or use the bootstrap script you ran). Push to GitHub.
2. In **Settings → Branches**, add branch protection for your default branch:
   - require PRs, 1+ approval (2 for risky areas), CI checks must pass
3. Create a **Project** (table/board) with fields: *Status*, *Sprint (iteration)*, *Story Points*.
4. Use **New Issue → User Story** to capture work; estimate points; link PRs with `Closes #123`.

## What’s baked in
- CI: Python (black, flake8, mypy, pytest) and Node (prettier, eslint, npm test) auto-run when those ecosystems are present
- Security: Dependabot (deps and actions). Optional CodeQL (enable in Actions tab)
- Policy: Conventional commits, review SLA, approvals, docs & tests required

See `docs/policies/coding-policy.md` for the full policy.
