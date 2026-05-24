# CameraSpoof - Complete File List

## Generated: 2026-05-24

### Root Directory
```
CameraSpoof/
├── .editorconfig
├── .gitattributes
├── .gitignore
├── build.bat
├── build.gradle.kts
├── build.sh
├── CHANGELOG.md
├── CONTRIBUTING.md
├── DEVELOPMENT.md
├── FAQ.md
├── gradlew
├── gradlew.bat
├── gradle.properties
├── INSTALLATION.md
├── LICENSE
├── PROJECT_SUMMARY.md
├── QUICKSTART.md
├── README.md
├── SECURITY.md
└── settings.gradle.kts
```

### GitHub Configuration
```
.github/
├── ISSUE_TEMPLATE/
│   ├── bug_report.yml
│   ├── config.yml
│   └── feature_request.yml
├── PULL_REQUEST_TEMPLATE.md
└── workflows/
    └── android.yml
```

### Gradle Wrapper
```
gradle/
└── wrapper/
    ├── gradle-wrapper.jar
    └── gradle-wrapper.properties
```

### App Module
```
app/
├── build.gradle.kts
├── proguard-rules.pro
└── src/
    ├── androidTest/
    │   └── java/
    │       └── com/cameraspoof/
    ├── main/
    │   ├── AndroidManifest.xml
    │   ├── assets/
    │   │   └── xposed_init
    │   ├── java/com/cameraspoof/
    │   │   ├── CameraSpoofApp.kt
    │   │   ├── MainActivity.kt
    │   │   ├── data/
    │   │   │   ├── local/
    │   │   │   │   ├── AppConfigDao.kt
    │   │   │   │   ├── AppConfigEntity.kt
    │   │   │   │   ├── AppDatabase.kt
    │   │   │   │   ├── LogDao.kt
    │   │   │   │   └── LogEntity.kt
    │   │   │   └── repository/
    │   │   │       └── AppRepository.kt
    │   │   ├── domain/
    │   │   │   ├── model/
    │   │   │   │   ├── AppInfo.kt
    │   │   │   │   └── LogEntry.kt
    │   │   │   └── usecase/
    │   │   │       ├── GetInstalledAppsUseCase.kt
    │   │   │       └── UpdateAppConfigUseCase.kt
    │   │   ├── ui/
    │   │   │   ├── components/
    │   │   │   │   ├── AppListItem.kt
    │   │   │   │   ├── LogItem.kt
    │   │   │   │   └── SearchBar.kt
    │   │   │   ├── screens/
    │   │   │   │   ├── LogsScreen.kt
    │   │   │   │   └── MainScreen.kt
    │   │   │   └── theme/
    │   │   │       ├── Color.kt
    │   │   │       ├── Theme.kt
    │   │   │       └── Type.kt
    │   │   ├── utils/
    │   │   │   ├── Constants.kt
    │   │   │   ├── FileUtils.kt
    │   │   │   ├── ImageUtils.kt
    │   │   │   ├── PermissionUtils.kt
    │   │   │   ├── PrefsHelper.kt
    │   │   │   └── XposedLogger.kt
    │   │   └── xposed/
    │   │       ├── CameraHook.kt
    │   │       └── XposedPrefs.kt
    │   └── res/
    │       ├── drawable/
    │       │   ├── ic_launcher_background.xml
    │       │   └── ic_launcher_foreground.xml
    │       ├── mipmap-anydpi-v26/
    │       │   ├── ic_launcher.xml
    │       │   └── ic_launcher_round.xml
    │       ├── mipmap-hdpi/
    │       ├── mipmap-mdpi/
    │       ├── mipmap-xhdpi/
    │       ├── mipmap-xxhdpi/
    │       ├── mipmap-xxxhdpi/
    │       ├── values/
    │       │   ├── arrays.xml
    │       │   ├── colors.xml
    │       │   ├── ic_launcher_background.xml
    │       │   ├── strings.xml
    │       │   └── themes.xml
    │       └── xml/
    │           ├── backup_rules.xml
    │           ├── data_extraction_rules.xml
    │           └── file_paths.xml
    └── test/
        └── java/
            └── com/cameraspoof/
```

## File Statistics

### Code Files
- **Kotlin Files**: 29
- **XML Files**: 14
- **Gradle Files**: 4
- **Total Lines of Code**: ~2,100+

### Documentation Files
- **Markdown Files**: 9
- **Total Documentation**: ~35 KB

### Configuration Files
- **GitHub Actions**: 1
- **Issue Templates**: 3
- **PR Template**: 1
- **EditorConfig**: 1
- **Git Files**: 2

### Build Files
- **Build Scripts**: 2 (bash + batch)
- **ProGuard**: 1
- **Gradle Wrapper**: 2

## Total Project Files: 59+

## Key Components

### Data Layer (7 files)
- Room Database
- DAOs
- Entities
- Repository

### Domain Layer (4 files)
- Models
- Use Cases

### UI Layer (9 files)
- Compose Screens
- Components
- Material 3 Theme

### Xposed Layer (2 files)
- Camera Hook
- Preferences Helper

### Utils (6 files)
- Image Processing
- File Management
- Logging
- Permissions
- Constants

### Resources (14 files)
- Strings
- Colors
- Themes
- XML Configs
- Drawables

## Build Outputs (Generated)
```
app/build/outputs/apk/
├── debug/
│   └── app-debug.apk
└── release/
    └── app-release.apk
```

## Project Size
- **Source Code**: ~150 KB
- **Documentation**: ~35 KB
- **Resources**: ~10 KB
- **Total (excluding build)**: ~200 KB
- **APK Size**: ~5-10 MB (estimated)

---

**Last Updated**: 2026-05-24
**Version**: 1.0.0
**Status**: Complete ✅
