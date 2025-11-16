#!/bin/bash

# Devcontainer Setup Test Script
# This script validates that the devcontainer is properly configured

set -e

echo "🧪 ======================================"
echo "   SAGA Devcontainer Setup Test"
echo "========================================"
echo ""

PASS=0
FAIL=0

# Helper functions
test_command() {
    local name="$1"
    local command="$2"
    local expected="$3"
    
    echo -n "Testing $name... "
    if eval "$command" &> /dev/null; then
        echo "✅ PASS"
        ((PASS++))
    else
        echo "❌ FAIL"
        ((FAIL++))
        [[ -n "$expected" ]] && echo "   Expected: $expected"
    fi
}

test_env_var() {
    local name="$1"
    local expected="$2"
    
    echo -n "Testing $name... "
    local actual="${!name}"
    if [[ "$actual" == "$expected" ]]; then
        echo "✅ PASS (value: $actual)"
        ((PASS++))
    else
        echo "❌ FAIL (expected: $expected, got: $actual)"
        ((FAIL++))
    fi
}

echo "🔧 Testing Development Tools"
echo "------------------------------"

# Test Java
echo -n "Testing Java 21... "
if java -version 2>&1 | grep -q "21"; then
    version=$(java -version 2>&1 | head -n 1)
    echo "✅ PASS ($version)"
    ((PASS++))
else
    echo "❌ FAIL (Java 21 not found)"
    ((FAIL++))
fi

# Test Maven
echo -n "Testing Maven... "
if mvn -version 2>&1 | grep -q "Apache Maven"; then
    version=$(mvn -version 2>&1 | grep "Apache Maven" | awk '{print $3}')
    echo "✅ PASS (Maven $version)"
    ((PASS++))
else
    echo "❌ FAIL (Maven not found)"
    ((FAIL++))
fi

# Test Docker
test_command "Docker CLI" "docker --version"

# Test kubectl
test_command "kubectl" "kubectl version --client"

# Test Helm
test_command "Helm" "helm version --short"

# Test PostgreSQL client
test_command "PostgreSQL client" "psql --version"

# Test Git
test_command "Git" "git --version"

# Test Node.js
echo -n "Testing Node.js... "
if node --version &> /dev/null; then
    version=$(node --version)
    echo "✅ PASS ($version)"
    ((PASS++))
else
    echo "⚠️  WARNING (Node.js not found - may still be installing)"
fi

echo ""
echo "🗄️  Testing Database"
echo "------------------------------"

# Test PostgreSQL connection
echo -n "Testing PostgreSQL connection... "
if pg_isready -h localhost -p 5432 -U admin &> /dev/null; then
    echo "✅ PASS (PostgreSQL is ready)"
    ((PASS++))
else
    echo "❌ FAIL (PostgreSQL not accessible)"
    ((FAIL++))
    echo "   Try: docker-compose -f .devcontainer/docker-compose.yml restart postgres"
fi

# Test database exists
echo -n "Testing database 'sighas'... "
if PGPASSWORD=admin psql -h localhost -U admin -d sighas -c "SELECT 1" &> /dev/null; then
    echo "✅ PASS (Database accessible)"
    ((PASS++))
else
    echo "❌ FAIL (Cannot connect to database)"
    ((FAIL++))
fi

echo ""
echo "🌍 Testing Environment Variables"
echo "------------------------------"

test_env_var "ACTIVE_PROFILE" "dev"
test_env_var "DB_HOST" "localhost"
test_env_var "DB_PORT" "5432"
test_env_var "DB_NAME" "sighas"
test_env_var "DB_USERNAME" "admin"
test_env_var "DB_PASSWORD" "admin"

echo ""
echo "📦 Testing Project Files"
echo "------------------------------"

# Test Maven wrapper
echo -n "Testing Maven wrapper... "
if [[ -x ./mvnw ]]; then
    echo "✅ PASS (mvnw is executable)"
    ((PASS++))
else
    echo "❌ FAIL (mvnw not executable)"
    ((FAIL++))
    echo "   Try: chmod +x ./mvnw"
fi

# Test pom.xml exists
test_command "pom.xml" "test -f pom.xml"

# Test source directory
test_command "src directory" "test -d src"

# Test devcontainer config
test_command "devcontainer.json" "test -f .devcontainer/devcontainer.json"

echo ""
echo "🔨 Testing Maven Build"
echo "------------------------------"

# Test Maven dependency resolution
echo -n "Testing Maven dependency resolution... "
if timeout 60s ./mvnw dependency:resolve -q &> /tmp/mvn-test.log; then
    echo "✅ PASS (Dependencies resolved)"
    ((PASS++))
else
    echo "⚠️  WARNING (Some dependencies may need downloading)"
    echo "   Run: ./mvnw dependency:go-offline"
fi

# Test Maven compile
echo -n "Testing Maven compile... "
if timeout 120s ./mvnw clean compile -q &> /tmp/mvn-compile.log; then
    echo "✅ PASS (Project compiles)"
    ((PASS++))
else
    echo "❌ FAIL (Compilation errors)"
    ((FAIL++))
    echo "   Check: /tmp/mvn-compile.log"
fi

echo ""
echo "📊 Testing Flyway"
echo "------------------------------"

echo -n "Testing Flyway migrations... "
if ./mvnw flyway:info -q &> /dev/null; then
    echo "✅ PASS (Flyway configured)"
    ((PASS++))
else
    echo "⚠️  WARNING (Flyway may need database running)"
fi

echo ""
echo "======================================"
echo "   Test Summary"
echo "======================================"
echo ""
echo "✅ Passed: $PASS"
echo "❌ Failed: $FAIL"
echo ""

if [ $FAIL -eq 0 ]; then
    echo "🎉 All tests passed! Your devcontainer is ready!"
    echo ""
    echo "Next steps:"
    echo "  1. Start the app: ./mvnw spring-boot:run"
    echo "  2. Open Swagger: http://localhost:8080/swagger-ui.html"
    echo "  3. Start coding! 🚀"
    exit 0
else
    echo "⚠️  Some tests failed. Check the errors above."
    echo ""
    echo "Common fixes:"
    echo "  - Wait a few minutes for setup to complete"
    echo "  - Rebuild container: F1 → Dev Containers: Rebuild Container"
    echo "  - Check .devcontainer/TEST_GUIDE.md for detailed troubleshooting"
    exit 1
fi





