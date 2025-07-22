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
OUTPUT_DIR=""
WEB_MODE=0
PORT="8081"

usage() {
  echo "Usage:"
  echo "  Serveur Web : ./scanner.sh --web [--port <port>]"
  echo "  Mode CLI    : ./scanner.sh -u <url> -f <json|text|html> [-o <output-dir>]"
  exit 1
}

# Argument parsing
while [[ $# -gt 0 ]]; do
  case "$1" in
    --web)
      WEB_MODE=1
      shift
      ;;
    --port)
      PORT="$2"
      shift 2
      ;;
    -u|--url)
      URL="$2"
      shift 2
      ;;
    -f|--format)
      FORMAT="$2"
      shift 2
      ;;
    -o|--output)
      OUTPUT_DIR="$2"
      shift 2
      ;;
    *)
      echo "❌ Unknown option: $1"
      usage
      ;;
  esac
done

# === Web mode ===
if [[ $WEB_MODE -eq 1 ]]; then
  echo "🚀 Lancement en mode serveur Web sur le port $PORT..."
  echo "🚀 Lancement du serveur Web (http://localhost:$PORT/)..."
  java -jar "$JAR_FILE" --web --port "$PORT"
  exit 0
fi

# === CLI mode ===
if [[ -z "$URL" || -z "$FORMAT" ]]; then
  echo "❌ Missing required arguments for CLI mode."
  usage
fi

echo "🚀 Scanning $URL with output format: $FORMAT"

if [[ -n "$OUTPUT_DIR" ]]; then
  echo "📂 Saving output to directory: $OUTPUT_DIR"
  java -jar "$JAR_FILE" --url "$URL" --format "$FORMAT" --output "$OUTPUT_DIR"
else
  java -jar "$JAR_FILE" --url "$URL" --format "$FORMAT"
fi
