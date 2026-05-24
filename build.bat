@echo off
REM CameraSpoof Build Script for Windows
REM This script builds the project and generates APKs

echo =========================================
echo CameraSpoof Build Script
echo =========================================
echo.

if not exist "gradlew.bat" (
    echo Error: gradlew.bat not found!
    echo Please ensure you're in the project root directory.
    exit /b 1
)

echo Building Debug APK...
call gradlew.bat clean assembleDebug --stacktrace

echo.
echo Building Release APK...
call gradlew.bat assembleRelease --stacktrace

echo.
echo =========================================
echo Build Complete!
echo =========================================
echo.
echo Debug APK: app\build\outputs\apk\debug\app-debug.apk
echo Release APK: app\build\outputs\apk\release\app-release.apk
echo.

pause
