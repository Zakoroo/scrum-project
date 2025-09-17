# syntax=docker/dockerfile:1.7

FROM maven:3.9-eclipse-temurin-21 AS dev
WORKDIR /workspace

# --- OS deps + Node once, with cache ---
# (These RUNs are cached unless the lines change.)
RUN --mount=type=cache,target=/var/cache/apt \
    --mount=type=cache,target=/var/lib/apt/lists \
    apt-get update && apt-get install -y --no-install-recommends curl \
    && curl -fsSL https://deb.nodesource.com/setup_22.x | bash - \
    && apt-get install -y --no-install-recommends nodejs \
    && rm -rf /var/lib/apt/lists/*

# --- Cache npm deps (if you have package.json) ---
COPY package.json package-lock.json* ./
RUN --mount=type=cache,target=/root/.npm \
    [ -f package.json ] && npm ci || true

# --- Cache Maven deps offline ---
COPY pom.xml ./
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -q -DskipTests dependency:go-offline

# --- Now copy the rest (this is the only cache-busting step when code changes) ---
COPY . .

# Default working dir is /workspace; no CMD so you can pass any mvn goals at runtime
