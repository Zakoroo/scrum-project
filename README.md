# Agile Project Starter (SCRUM) — Java/Gradle/JavaFX Edition

A ready-to-use GitHub repository template for Scrum-style agile projects. It ships with:

- Issue forms for **User Stories**, **Bugs**, **Tasks**, **Spikes**
- A PR template aligned with **Conventional Commits**
- **CI** (GitHub Actions) that runs formatter/linter/tests for Java (Gradle), plus Python/Node.js if present
- **CODEOWNERS**, **branch protections** (configure in Settings), and a documented **Definition of Done**
- Docs for team working agreements and review etiquette

> **Scrum terms at a glance**
> - **Product Backlog Item (PBI)**: work item such as a story, bug, or spike.
> - **User Story**: end-user value slice: *As a [role], I want [capability], so that [benefit].*
> - **Sprint**: timebox (e.g., 1–2 weeks) where PBIs are delivered.
> - **Definition of Ready (DoR)**: checklist a story must meet before sprint commitment.
> - **Definition of Done (DoD)**: quality bar to consider work complete.

---

## 🚀 Contributor Quick Start (copy/paste)

### 0) Requirements
- **JDK 21** (Temurin recommended)
- **Git** and a GitHub account
- Optional IDE: IntelliJ IDEA (Community/Ultimate) with Gradle support

### 1) Clone and set up
```bash
# SSH (preferred)
git clone git@github.com:YOUR_USER/scrum-project.git
cd scrum-project

# or HTTPS
# git clone https://github.com/YOUR_USER/scrum-project.git
# cd scrum-project
```

### 2) Install local Git hooks (one-time)
These keep commits clean before they ever hit CI.
```bash
# Pre-commit: runs ./gradlew check (compile, tests, Checkstyle, SpotBugs)
./scripts/install-git-hooks.sh

# Commit message validation: enforce Conventional Commits locally
./scripts/install-commit-msg-hook.sh
```

### 3) Build & test locally
```bash
./gradlew build
```
This compiles, runs unit tests (JUnit 5), and executes Checkstyle + SpotBugs.

### 4) Run the JavaFX app
```bash
./gradlew run
```

### 5) Create a feature branch, commit, and push
```bash
git checkout -b feat/short-branch-name
# ...edit code...

git add -A
# Conventional Commits format: <type>(<scope>)!: <subject>
# examples: "feat(ui): add dark mode toggle"  |  "fix(auth): handle token refresh"

git commit -m "feat(ui): add dark mode toggle"
git push -u origin feat/short-branch-name
```

### 6) Open a Pull Request
- Title should be **Conventional Commits** style.
- In the PR description, link the issue: `Closes #123`.
- Ensure CI is green; request reviews as needed.

---

## 🧭 Project Layout
```
src/
  main/java/...        # app code
  main/resources/      # FXML, icons, etc.
  test/java/...        # tests (JUnit 5)
```

---

## 🤖 CI
- Runs on pushes/PRs to `master`.
- Jobs:
  - **java (Gradle)**: build + test + Checkstyle + SpotBugs
  - **python/node** jobs auto-run if those ecosystems are detected
- **CodeQL** security scan enabled (maintainer configuration).

---

## 🧪 Testing & Quality
- Unit tests: JUnit 5 (place under `src/test/java`)
- Static analysis: Checkstyle, SpotBugs (`./gradlew check`)
- Run everything locally before pushing:
  ```bash
  ./gradlew build
  ```

---

## 📝 Conventional Commits (commit messages)
**Format**
```
<type>(<optional-scope>)<optional-!>: <subject>
```
**Types**: `feat`, `fix`, `docs`, `style`, `refactor`, `perf`, `test`, `build`, `ci`, `chore`, `revert`

**Examples**
- `feat(ui): add dark mode toggle`
- `fix: null-check user session`
- `refactor(core)!: remove deprecated parser`

Both a local `commit-msg` hook and a CI workflow validate this.

---

## 🔐 Branching, Reviews & DoD
- Short-lived feature branches; open PRs into `master`.
- Approvals: 1 reviewer for routine, 2 for risky areas (security/DB/public APIs).
- **Definition of Done**:
  - ✅ CI green (build, tests, static analysis)
  - ✅ Approved PR (per rules above)
  - ✅ Docs updated (README/inline/migration notes)
  - ✅ Issue linked (`Closes #123`)
  - ✅ No secrets/keys in repo (use env/secret manager)

---

## 🧩 Issue Templates & Labels
- Create work via **New Issue** using templates: *User Story*, *Bug*, *Task*, *Spike*.
- Labels available: `type: user story|bug|task|spike`, `status: backlog|ready|in progress|in review|done`, `priority: high|medium|low`.

---

## 🗂️ Project Board (Scrum)
- GitHub Project with fields: **Status**, **Sprint (Iteration)**, **Story Points**.
- Views:
  - **Board – Current Sprint** grouped by Status
  - **Backlog (Table)** filtered to `Status=Backlog`

---

## 🛠️ Developer Tips
- Always run via **Gradle Wrapper**: `./gradlew ...` (pinned version, reproducible builds)
- Keep JavaFX controllers thin; push logic to testable services
- Prefer small PRs; respond to review comments and resolve threads

---

## 📄 Reference
- Coding policy: `docs/policies/coding-policy.md`
- Contributing guide: `CONTRIBUTING.md`
- Security policy: `SECURITY.md`
- License: `LICENSE`
