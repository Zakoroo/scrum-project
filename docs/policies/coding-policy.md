# Climate Game

A Java-based strategy/simulation game focused on climate action and education. Developed using Agile Scrum practices to enable iterative development and collaboration.

---

## Project Overview

Climate Game engages players in decision-making to address climate change. This repository contains the Java backend, a sample UI (JavaFX), tests, and assets for Scrum workflow management.

## Features

- Core Java codebase (JavaFX demo app and unit tests)
- Issue/PR templates for Scrum workflows (stories, bugs, tasks, spikes)
- Automated CI/CD workflows (Java build/test out of the box)
- Label bootstrap scripts and CODEOWNERS setup
- Contributor, coding, and security policies

## Directory Structure

For details, see [`PROJECT_INFO/source-tree.txt`](PROJECT_INFO/source-tree.txt).

## Getting Started

1. **Clone the repo:**
   ```bash
   git clone https://github.com/Zakoroo/scrum-project.git
   cd scrum-project
   ```

2. **Java quick start (Maven):**
   ```bash
   mvn clean verify
   mvn -q javafx:run
   ```

## Scrum & CI Workflow

- Sprints: 1 weeks, regular planning, reviews, retrospectives
- Board: Backlog → Selected for Sprint → In Progress → In Review → Done
- CI: Automated build/test on every push/PR (Java workflows enabled)
- Definition of Done: Code, tests, review, CI green, docs updated

## Contribution Guidelines

- See [CONTRIBUTING.md](CONTRIBUTING.md) for branching, commits, and PR etiquette
- Coding conventions and review process in [docs/policies/coding-policy.md](docs/policies/coding-policy.md)
- Security policy in [SECURITY.md](SECURITY.md)

## Labels & Reviews

- Labels: story, bug, task, spike, P1–P3, gameplay, ui, infra, docs, good-first-issue
- Review: At least 1 reviewer (2 for risky changes), CODEOWNERS enforced for critical paths

## Notes

- Shell scripts use LF endings and POSIX shells (Git Bash works on Windows)
- Commit style: Conventional Commits recommended

## License

MIT License — see [LICENSE](LICENSE)
