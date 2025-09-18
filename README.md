# Climate Game — Agile Scrum Project

A Scrum-driven climate strategy/simulation game. This repo support CI pipelines to support iterative delivery.

## What’s included

- Issue templates for User Stories, Bugs, Tasks, Spikes
- PR template
- CI workflows that auto-activate when language-specific configs exist (Java/Node/Python, etc.)
- Script to bootstrap repository labels

## Project layout

[project layout](/PROJECT_INFO/source-tree.txt)

Note: For Java projects, prefer Maven/Gradle layout: src/main/java and src/test/java.

## Quick start

1. Clone

```bash
git clone <your-repo-url>
cd scrum-project
```

2. Add your language and tooling

- Use any build system or runtime you prefer (e.g., Gradle/Maven, npm/pnpm/yarn, Poetry/pip, etc.).
- Add config files when ready (e.g., build.gradle, package.json, pyproject.toml).

## Commands

- List commands: `dev/do.sh help` (macOS/Linux) or `dev\do.ps1 help` (Windows)
- Build: `dev/do.sh build`
- Run: `dev/do.sh run`

## Java quick start (optional)

- Run tests
   - Maven: mvn test
   - Gradle: ./gradlew test (or gradlew.bat test on Windows)
- Run a specific test
   - Maven: mvn -Dtest=AppTest test
   - Gradle: ./gradlew test --tests com.ecologicstudios.AppTest

## VS Code tips (Java auto-import)

If imports are added automatically and you want to stop that, update your user settings (File > Preferences > Settings or settings.json):

```jsonc
{
   // Turn off auto-import suggestions
   "java.completion.imports.enabled": false,
   // Do not organize imports on save
   "java.saveActions.organizeImports": false,
   // Also disable organize imports on save for Java
   "[java]": {
      "editor.codeActionsOnSave": {
         "source.organizeImports": false,
      },
   },
}
```

Tip: Run “Developer: Reload Window” after changing settings.

## CI

- Runs on pushes/PRs to main branches.
- Jobs auto-activate based on files present:
   - Java: when a Gradle/Maven build file exists
   - Node: when package.json exists
   - Python: when pyproject.toml or requirements\*.txt exists
- See workflows under: .github/workflows/

## Scrum workflow

- Cadence: 1–2 week sprints with Sprint Planning, Daily Scrum, Review, Retrospective.
- Board flow: Backlog → Selected for Sprint → In Progress → In Review → Done.
- Definition of Ready (DoR): clear acceptance criteria, dependencies identified, sized.
- Definition of Done (DoD): code + tests + review + CI green + docs updated.

## Labels and reviews

- Bootstrap default labels via: [scripts/bootstrap-labels.sh](scripts/bootstrap-labels.sh)
- CODEOWNERS: [.github/CODEOWNERS](.github/CODEOWNERS)
- Label conventions:
   - Type: story, bug, task, spike
   - Priority: P1, P2, P3
   - Area: gameplay, ui, infra, docs
   - Good first issue: good-first-issue

## Policies and docs

- Coding policy: [docs/policies/coding-policy.md](docs/policies/coding-policy.md)
- Contributing: [CONTRIBUTING.md](CONTRIBUTING.md)
- Code of Conduct: [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md)
- Security: [SECURITY.md](SECURITY.md)
- License: [LICENSE](LICENSE)

## Notes

- Shell scripts use LF endings and are intended for POSIX shells (Git Bash on Windows works). See [.gitattributes](.gitattributes).
- Commit message style is not enforced; follow any convention your team prefers (Conventional Commits recommended but optional).
