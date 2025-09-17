# Agile Project Starter (SCRUM) — Language-agnostic Template

A lightweight GitHub template for Scrum-style projects with issue templates, PR hygiene, and CI scaffolding. No build tool or language is enforced.

## What’s included
- Issue templates for User Stories, Bugs, Tasks, Spikes
- PR template
- CI workflows that auto-activate when language-specific configs exist (Java/Node/Python, etc.)
- Script to bootstrap repository labels

## Repository layout
```
.editorconfig
.gitattributes
.github/
  CODEOWNERS
  dependabot.yml
  PULL_REQUEST_TEMPLATE.md
  ISSUE_TEMPLATE/
  workflows/
docs/
  policies/
scripts/
  bootstrap-labels.sh
src/
tests/
```

## Quick start
1) Clone
```bash
git clone <your-repo-url>
cd scrum-project
```

2) Add your language and tooling
- Use any build system or runtime you prefer (e.g., Gradle/Maven, npm/pnpm/yarn, Poetry/pip, etc.).
- Add config files when ready (e.g., build.gradle, package.json, pyproject.toml).

## Commands
- List commands: `dev/do.sh help` (macOS/Linux) or `dev\do.ps1 help` (Windows)
- Build: `dev/do.sh build`
- Run:   `dev/do.sh run`

## CI
- Runs on pushes/PRs to main branches.
- Jobs auto-activate based on files present:
  - Java: when a Gradle/Maven build file exists
  - Node: when package.json exists
  - Python: when pyproject.toml or requirements*.txt exists
- See workflows under: .github/workflows/

## Labels and reviews
- Bootstrap default labels via: [scripts/bootstrap-labels.sh](scripts/bootstrap-labels.sh)
- CODEOWNERS: [.github/CODEOWNERS](.github/CODEOWNERS)

## Policies and docs
- Coding policy: [docs/policies/coding-policy.md](docs/policies/coding-policy.md)
- Contributing: [CONTRIBUTING.md](CONTRIBUTING.md)
- Code of Conduct: [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md)
- Security: [SECURITY.md](SECURITY.md)
- License: [LICENSE](LICENSE)

## Notes
- Shell scripts use LF endings and are intended for POSIX shells (Git Bash on Windows works). See [.gitattributes](.gitattributes).
- Commit message style is not enforced; follow any convention your team prefers (Conventional Commits recommended but optional).