# CameraSpoof

Production-ready Android LSPosed/Xposed module for spoofing camera images in selected applications.

## Features

- 📱 Spoof camera images in any selected app
- 🎯 Per-app configuration with enable/disable toggle
- 📷 Support for front camera, back camera, or all cameras
- 🖼️ Image preview and management
- 📊 Comprehensive logging system
- 🌙 Material 3 Dark Theme UI
- 💾 Persistent configuration across reboots
- 🔍 App search functionality

## Requirements

- Android 10 - 15 (API 29-35)
- LSPosed or Xposed Framework
- Magisk/Zygisk (recommended)
- Root access

## Installation

1. Install LSPosed/Xposed Framework
2. Download the latest APK from [Releases](../../releases)
3. Install the APK
4. Enable the module in LSPosed Manager
5. Select target applications in LSPosed scope
6. Reboot device

## Usage

1. Open CameraSpoof app
2. Search and select the app you want to spoof
3. Toggle the switch to enable spoofing
4. Select an image from your gallery
5. Choose spoof mode (Front/Back/All cameras)
6. Open the target app and use the camera

## Building

### Local Build

```bash
git clone https://github.com/yourusername/CameraSpoof.git
cd CameraSpoof
./gradlew assembleDebug
```

### GitHub Actions

The project includes automated CI/CD pipeline that builds APKs on every push.

## Architecture

- **MVVM** architecture pattern
- **Jetpack Compose** for UI
- **Room** for local database
- **Coroutines** and **Flow** for async operations
- **Repository** pattern for data management

## Compatibility

- ✅ Android 10 (API 29)
- ✅ Android 11 (API 30)
- ✅ Android 12/12L (API 31-32)
- ✅ Android 13 (API 33)
- ✅ Android 14 (API 34)
- ✅ Android 15 (API 35)

## Supported Camera APIs

- Camera2 API
- Camera1 API (legacy)

## Logs

The app includes a comprehensive logging system:
- Hook success/failure logs
- Active package detection
- Camera API detection
- Image injection logs
- Export logs to text file

## Safety

- Does not break system camera functionality
- Only affects selected applications
- Graceful error handling
- No bootloop risk
- Fallback behavior on errors

## License

This project is for educational purposes only. Use responsibly and in accordance with local laws.

## Disclaimer

This module is provided as-is without any warranty. The developers are not responsible for any misuse or damage caused by this module.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For issues and feature requests, please use the [GitHub Issues](../../issues) page.
