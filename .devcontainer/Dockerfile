# Use Ubuntu as base image
FROM mcr.microsoft.com/devcontainers/base:ubuntu-22.04

# Set up non-root user
ARG USERNAME=vscode
ARG USER_UID=1000
ARG USER_GID=$USER_UID

# Install basic dependencies
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends \
    curl \
    wget \
    ca-certificates \
    git \
    zip \
    unzip \
    gnupg \
    software-properties-common \
    postgresql-client \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Install Lombok for VSCode (Java extension will be installed via features)
RUN mkdir -p /usr/local/share/lombok \
    && wget -O /usr/local/share/lombok/lombok.jar https://projectlombok.org/downloads/lombok.jar

# Set up workspace
RUN mkdir -p /workspace && chown ${USERNAME}:${USERNAME} /workspace

USER ${USERNAME}

WORKDIR /workspace


