#!/bin/bash

# === Configuration ===
TARGET_DIR="target"
JAR_NAME="security-headers-scanner-1.0.0.jar"
JAR_FILE="$TARGET_DIR/$JAR_NAME"

# === Check Java Installed ===
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed or not in PATH"
    exit 1
fi

# === Clean & Build the project using maven ===
echo "🔧 Building the project with Maven..."
if ! mvn clean package -DskipTests; then
    echo "❌ Maven build failed."
    exit 1
fi

# === Check JAR Exists ===
if [[ ! -f "$JAR_FILE" ]]; then
    echo "❌ $JAR_FILE not found"
    exit 1
fi

# === Parse Arguments ===
URL=""
FORMAT=""

while [[ $# -gt 0 ]]; do
  case "$1" in
    --url)
      URL="$2"
      shift 2
      ;;
    --format)
      FORMAT="$2"
      shift 2
      ;;
    *)
      echo "❌ Unknown option: $1"
      echo "Usage: ./scanner --url <url> --format <json|text|html>"
      exit 1
      ;;
  esac
done

# === Validate the input arguments ===
if [[ -z "$URL" || -z "$FORMAT" ]]; then
    echo "❌ Missing required arguments."
    echo "Usage: ./scanner --url <url> --format <json|text|html>"
    exit 1
fi

# === Run the Scanner ===
echo "🚀 Scanning $URL with output format: $FORMAT"
clear
java -jar "$JAR_FILE" --url "$URL" --format "$FORMAT"
