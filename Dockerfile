# Docker build/runtime: JDK 21 + Maven
FROM maven:3.9-eclipse-temurin-21
WORKDIR /workspace
# If you also need Node for a frontend build, uncomment:
RUN apt-get update && apt-get install -y curl \
    && curl -fsSL https://deb.nodesource.com/setup_22.x | bash - \
    && apt-get install -y nodejs && rm -rf /var/lib/apt/lists/*
