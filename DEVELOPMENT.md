# CameraSpoof - Development Notes

## Project Structure

```
CameraSpoof/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/cameraspoof/
│   │   │   │   ├── data/
│   │   │   │   │   ├── local/          # Room database entities and DAOs
│   │   │   │   │   └── repository/     # Repository pattern implementation
│   │   │   │   ├── domain/
│   │   │   │   │   └── model/          # Domain models
│   │   │   │   ├── ui/
│   │   │   │   │   ├── components/     # Reusable Compose components
│   │   │   │   │   ├── screens/        # Screen composables
│   │   │   │   │   └── theme/          # Material 3 theme
│   │   │   │   ├── utils/              # Utility classes
│   │   │   │   ├── xposed/             # Xposed hook implementation
│   │   │   │   ├── CameraSpoofApp.kt   # Application class
│   │   │   │   └── MainActivity.kt     # Main activity
│   │   │   ├── assets/
│   │   │   │   └── xposed_init         # Xposed module entry point
│   │   │   ├── res/                    # Android resources
│   │   │   └── AndroidManifest.xml
│   │   ├── androidTest/                # Instrumented tests
│   │   └── test/                       # Unit tests
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── gradle/
│   └── wrapper/
├── .github/
│   └── workflows/
│       └── android.yml                 # CI/CD pipeline
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── README.md
```

## Key Components

### 1. Xposed Hook (CameraHook.kt)
- Hooks Camera2 API and Camera1 API
- Intercepts image capture and replaces with selected image
- Supports per-app configuration
- Handles front/back/all camera modes

### 2. UI Layer
- **MainScreen**: App list with search and configuration
- **LogsScreen**: View and export logs
- **AppListItem**: Individual app configuration card
- Material 3 Dark Theme

### 3. Data Layer
- **Room Database**: Persistent storage for app configs and logs
- **Repository**: Single source of truth for data operations
- **SharedPreferences**: World-readable prefs for Xposed module

### 4. Utilities
- **ImageUtils**: Image processing and storage
- **FileUtils**: File operations and cleanup
- **XposedLogger**: Logging for hook operations
- **PrefsHelper**: SharedPreferences management

## Building

### Prerequisites
- JDK 17
- Android SDK (API 34)
- Android Studio (optional)

### Local Build
```bash
./gradlew assembleDebug
./gradlew assembleRelease
```

### GitHub Actions
Automatically builds on push to main/develop branches.

## Testing

### Manual Testing Checklist
1. Install module and enable in LSPosed
2. Select target app in LSPosed scope
3. Reboot device
4. Open CameraSpoof app
5. Enable spoofing for target app
6. Select image
7. Choose spoof mode
8. Open target app and test camera
9. Check logs for hook activity

### Known Limitations
- Camera2 Image wrapping is simplified (may need enhancement)
- Some apps with custom camera implementations may not work
- Performance depends on image size and device capabilities

## Security Considerations
- Uses MODE_WORLD_READABLE for SharedPreferences (required for Xposed)
- Images stored in app private directory
- No network permissions
- No sensitive data collection

## Future Enhancements
- Video spoofing support
- Multiple images per app (rotation)
- Real-time image effects
- Better Camera2 Image implementation
- Support for more camera APIs

## Troubleshooting

### Module not working
1. Check LSPosed activation
2. Verify app is in module scope
3. Check logs for errors
4. Ensure image is selected
5. Reboot device

### App crashes
1. Check logcat for stack traces
2. Verify image file exists
3. Check camera API compatibility
4. Disable module and test

### Performance issues
1. Use smaller images
2. Check device resources
3. Reduce image quality in ImageUtils
4. Clear old logs

## License
Educational purposes only. Use responsibly.
