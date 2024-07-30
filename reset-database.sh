#!/bin/bash

# Define database name and owner
DB_NAME="fitmaster-database"
DB_OWNER="admin"

# Define PostgreSQL user and password (optional, for authentication)
PG_USER="postgres"
#PG_PASS="your_postgres_password"

# Export the password to avoid prompt for password
#export PGPASSWORD=$PG_PASS

# Function to terminate all connections to the database
terminate_connections() {
  echo "Terminating connections to $DB_NAME..."
  psql -U $PG_USER -d postgres -c "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = '$DB_NAME' AND pid <> pg_backend_pid();"
}

# Function to drop the database
drop_database() {
  echo "Dropping existing database $DB_NAME..."
  psql -U $PG_USER -d postgres -c "DROP DATABASE IF EXISTS \"$DB_NAME\";"
}

# Function to create the database
create_database() {
  echo "Creating new database $DB_NAME..."
  psql -U $PG_USER -d postgres -c "CREATE DATABASE \"$DB_NAME\" WITH OWNER = $DB_OWNER ENCODING = 'UTF8' LOCALE_PROVIDER = 'libc' CONNECTION LIMIT = -1 IS_TEMPLATE = False;"
}

# Execute the functions
terminate_connections
drop_database
create_database
echo "Database reset complete."

# Unset the password variable
unset PGPASSWORD

# Get the directory of the current script
SCRIPT_DIR="$(dirname "$(realpath "$0")")"

# Path to the properties file relative to the script location
ENV_FILE="$SCRIPT_DIR/src/main/resources/env-dev.properties"
MIGRATION_LOCATIONS="$SCRIPT_DIR/src/main/resources/database/migrations"

# Function to load relevant properties from the file
load_properties() {
 DB_NAME=$(grep "^DB_NAME=" "$ENV_FILE" | sed 's/^DB_NAME=//')
 DB_PORT=$(grep "^DB_PORT=" "$ENV_FILE" | sed 's/^DB_PORT=//')
 DB_URL=$(grep "^DB_URL=" "$ENV_FILE" | sed -e "s/^DB_URL=//" -e "s/\${DB_PORT}/$DB_PORT/" -e "s/\${DB_NAME}/$DB_NAME/")
 DB_USERNAME=$(grep "^DB_USERNAME=" "$ENV_FILE" | sed 's/^DB_USERNAME=//')
 DB_PASSWORD=$(grep "^DB_PASSWORD=" "$ENV_FILE" | sed 's/^DB_PASSWORD=//')
}

# Load properties
load_properties

echo $DB_URL
echo $DB_USERNAME
echo $DB_PASSWORD
echo $MIGRATION_LOCATIONS

# Run Flyway migrate
echo "Running Flyway migrations..."
flyway -url="$DB_URL" -user="$DB_USERNAME" -password="$DB_PASSWORD" -locations=filesystem:"$MIGRATION_LOCATIONS" migrate

echo "Flyway migrations complete."

