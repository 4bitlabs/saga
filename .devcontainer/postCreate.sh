#!/bin/bash

# Post-create script for SAGA devcontainer
echo "🚀 Running post-create setup for SAGA..."

# Wait for PostgreSQL to be ready
echo "⏳ Waiting for PostgreSQL to be ready..."
for i in {1..30}; do
  if pg_isready -h localhost -p 5432 -U admin &> /dev/null; then
    echo "✅ PostgreSQL is ready!"
    break
  fi
  echo "   Attempt $i/30: PostgreSQL not ready yet..."
  sleep 2
done

# Display Java and Maven versions
echo "📦 Installed versions:"
java -version 2>&1 | head -n 1
mvn -version | head -n 1

# Set executable permissions on mvnw
chmod +x ./mvnw

# Download Maven dependencies (speeds up first build)
echo "📥 Downloading Maven dependencies..."
./mvnw dependency:go-offline -B || echo "⚠️  Some dependencies may need to be downloaded later"

# Display database connection info
echo ""
echo "🗄️  Database Connection Info:"
echo "   Host: localhost"
echo "   Port: 5432"
echo "   Database: sighas"
echo "   Username: admin"
echo "   Password: admin"
echo ""

# Display useful commands
echo "💡 Useful commands:"
echo "   Start app:     ./mvnw spring-boot:run"
echo "   Run tests:     ./mvnw test"
echo "   Build JAR:     ./mvnw clean package"
echo "   Build Docker:  docker build -t saga ."
echo "   DB migrations: ./mvnw flyway:info"
echo "   Swagger UI:    http://localhost:8080/swagger-ui/index.html"
echo ""
echo "📝 Note: Flyway migrations run automatically via Maven"
echo ""

# Verify Flyway is properly configured
echo "🔍 Verifying Flyway configuration..."
if [ "$SPRING_FLYWAY_ENABLED" = "true" ]; then
  echo "✅ Flyway is ENABLED (migrations will run automatically)"
  echo "   DDL Auto: $SPRING_JPA_HIBERNATE_DDL_AUTO"
else
  echo "⚠️  Flyway is DISABLED - migrations will not run!"
fi
echo ""

echo "✨ Setup complete! Happy coding! 🎉"


