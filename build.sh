#!/bin/bash

# CameraSpoof Build Script
# This script builds the project and generates APKs

set -e

echo "========================================="
echo "CameraSpoof Build Script"
echo "========================================="
echo ""

# Check if gradlew exists
if [ ! -f "./gradlew" ]; then
    echo "Error: gradlew not found!"
    echo "Please ensure you're in the project root directory."
    exit 1
fi

# Make gradlew executable
chmod +x ./gradlew

echo "Building Debug APK..."
./gradlew clean assembleDebug --stacktrace

echo ""
echo "Building Release APK..."
./gradlew assembleRelease --stacktrace

echo ""
echo "========================================="
echo "Build Complete!"
echo "========================================="
echo ""
echo "Debug APK: app/build/outputs/apk/debug/app-debug.apk"
echo "Release APK: app/build/outputs/apk/release/app-release.apk"
echo ""
