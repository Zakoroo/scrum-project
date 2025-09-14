#!/usr/bin/env bash
set -euo pipefail

# Requires: gh CLI, authenticated (gh auth login)
# Usage: ./scripts/bootstrap-labels.sh <owner/repo>

REPO=${1:?"Usage: $0 <owner/repo>"}

create() {
  local name="$1" desc="$2" color="$3"
  gh label create "$name" --description "$desc" --color "$color" --repo "$REPO" 2>/dev/null || gh label edit "$name" --description "$desc" --color "$color" --repo "$REPO"
}

create "type: user story" "End-user value increment" "1f883d"
create "type: bug" "Defect needing fix" "d73a4a"
create "type: spike" "Time-boxed research" "5319e7"
create "type: task" "Technical task" "ededed"
create "priority: high" "High priority" "b60205"
create "priority: medium" "Medium priority" "fbca04"
create "priority: low" "Low priority" "0e8a16"
create "status: backlog" "In backlog" "cfd3d7"
create "status: ready" "Ready for sprint" "a2eeef"
create "status: in progress" "Actively being worked" "5319e7"
create "status: in review" "Awaiting code review" "d4c5f9"
create "status: done" "Meets DoD" "0e8a16"
