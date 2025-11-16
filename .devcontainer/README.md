# SAGA Development Container

This directory contains the Dev Container configuration for the SAGA (Sistema Acadêmico de Gestão e Alocação) project.

## What's Included

### Development Environment
- **Java 21** (Temurin distribution via devcontainer features)
- **Maven 3.9.x** for build management
- **PostgreSQL 17.6** database
- **Docker-in-Docker** for container operations
- **kubectl & Helm** for Kubernetes development
- **GitHub CLI** for GitHub operations
- **Node.js LTS** for any frontend tooling needs
- **Flyway** for database migrations (via Maven plugin)

### VS Code Extensions
- Java Extension Pack (IntelliSense, debugging, testing)
- Spring Boot Dashboard & Tools
- Lombok Support
- PostgreSQL client tools
- Docker & Kubernetes tools
- YAML & XML support
- GitLens for advanced Git features
- REST Client for API testing

## Getting Started

### Prerequisites
- [Visual Studio Code](https://code.visualstudio.com/)
- [Dev Containers extension](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)
- [Docker Desktop](https://www.docker.com/products/docker-desktop) or Docker Engine

### Opening the Project

1. Open VS Code
2. Open this project folder
3. When prompted, click **"Reopen in Container"**
   - Or use Command Palette (F1): `Dev Containers: Reopen in Container`

The first build may take 5-10 minutes as it downloads and configures everything.

### What Happens on First Load

1. Docker builds the development container with Java 21 and all tools
2. PostgreSQL 17.6 starts in a separate container
3. Maven dependencies are downloaded
4. Database migrations run automatically via Flyway
5. VS Code extensions are installed

## Using the Environment

### Running the Application

```bash
# Start Spring Boot application
./mvnw spring-boot:run

# Or with hot reload
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true"
```

The application will be available at:
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- Actuator: http://localhost:8080/actuator/health

### Database Access

The PostgreSQL database is automatically configured and accessible at:
- **Host**: `localhost` (inside container) or `127.0.0.1` (from host)
- **Port**: `5432`
- **Database**: `sighas`
- **Username**: `admin`
- **Password**: `admin`

Use the pre-configured SQLTools connection in VS Code:
1. Click the database icon in the left sidebar
2. Select "PostgreSQL - SAGA Dev"
3. Execute queries!

### Maven Commands

```bash
# Build the project
./mvnw clean package

# Run tests
./mvnw test

# Clean build
./mvnw clean install

# Skip tests
./mvnw package -DskipTests

# Update dependencies
./mvnw versions:display-dependency-updates
```

### Database Migrations

```bash
# View migration status
./mvnw flyway:info

# Run migrations
./mvnw flyway:migrate

# Clean database (⚠️ DESTRUCTIVE)
./mvnw flyway:clean

# Validate migrations
./mvnw flyway:validate
```

### Docker Commands

```bash
# Build Docker image
docker build -t saga:local .

# Run with docker-compose (from project root)
docker-compose up -d

# View logs
docker-compose logs -f app
```

### Kubernetes/Helm

```bash
# Validate Helm chart
helm lint helm/saga-chart

# Dry run
helm install saga-dev helm/saga-chart --values helm/saga-chart/values-dev.yaml --dry-run

# Deploy to local cluster
helm install saga-dev helm/saga-chart --values helm/saga-chart/values-dev.yaml
```

## Environment Variables

The following environment variables are pre-configured in the devcontainer:

- `ACTIVE_PROFILE=dev`
- `DB_HOST=localhost`
- `DB_PORT=5432`
- `DB_NAME=sighas`
- `DB_USERNAME=admin`
- `DB_PASSWORD=admin`

Override them in your terminal if needed, or modify `.devcontainer/devcontainer.json`.

## Troubleshooting

### Container won't start
```bash
# Rebuild container without cache
Dev Containers: Rebuild Container Without Cache
```

### Database connection issues
```bash
# Check if PostgreSQL is running
pg_isready -h localhost -p 5432 -U admin

# Restart PostgreSQL
docker-compose restart postgres
```

### Maven issues
```bash
# Clear Maven cache
rm -rf ~/.m2/repository

# Re-download dependencies
./mvnw dependency:purge-local-repository
./mvnw dependency:resolve
```

### Java version issues
```bash
# Verify Java version
java -version

# Should show: openjdk version "21.x.x" (Temurin)

# If Java is not found, rebuild the container
# F1 → Dev Containers: Rebuild Container
```

## Customization

### Adding VS Code Extensions
Edit `.devcontainer/devcontainer.json` and add extension IDs to the `extensions` array.

### Changing Java/Maven Versions
Edit `.devcontainer/devcontainer.json` and modify the `features` section for Java.

### Adding More Services
Edit `.devcontainer/docker-compose.yml` to add Redis, RabbitMQ, etc.

## Project Structure

```
.devcontainer/
├── devcontainer.json     # Main configuration
├── docker-compose.yml    # Service definitions (app + postgres)
├── Dockerfile           # Container image definition
├── postCreate.sh        # Post-creation setup script
└── README.md           # This file
```

## Resources

- [Dev Containers Documentation](https://containers.dev/)
- [VS Code Java Documentation](https://code.visualstudio.com/docs/java/java-tutorial)
- [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools)
- [Flyway Documentation](https://flywaydb.org/documentation/)

## Support

For issues or questions about the devcontainer setup, please open an issue in the project repository.


