#!/usr/bin/env bash
set -euo pipefail

IMAGE_DEFAULT="ecologicstudios/climategame-build:latest"
IMAGE_RAW="${IMAGE:-$IMAGE_DEFAULT}"
IMAGE="$(printf '%s' "$IMAGE_RAW" | tr '[:upper:]' '[:lower:]')"

DOCKER_BUILD_CONTEXT="${DOCKER_BUILD_CONTEXT:-.}"
CONTAINER_WORKDIR="/workspace"

host_pwd="$PWD"

MVN_FLAGS="${MVN_FLAGS:--ntp -B}"
if [ "${QUIET:-0}" = "1" ]; then
  MVN_FLAGS="$MVN_FLAGS -q"
fi

case "${OSTYPE:-}" in
  msys*|cygwin*|win32*)
    # 1) Get a Windows-style absolute path for the volume mount
    if command -v pwd >/dev/null 2>&1 && pwd -W >/dev/null 2>&1; then
      host_pwd="$(pwd -W)"
    elif command -v cygpath >/dev/null 2>&1; then
      host_pwd="$(cygpath -w "$PWD")"
    fi
    # 2) Stop Git Bash from rewriting -v/-w arguments
    export MSYS_NO_PATHCONV=1
    export MSYS2_ARG_CONV_EXCL="*"
    # 3) Use //workspace to avoid accidental rewriting
    workdir_arg='-w //workspace'
    ;;
  *)
    workdir_arg='-w /workspace'
    ;;
esac

# Build the image if missing
if ! docker image inspect "$IMAGE" >/dev/null 2>&1; then
  echo "Building image '$IMAGE'..."
  docker build -t "$IMAGE" "$DOCKER_BUILD_CONTEXT"
fi

docker run --rm -it \
  -v "$host_pwd:/workspace" \
  $workdir_arg \
  "$IMAGE" mvn $MVN_FLAGS "$@"
