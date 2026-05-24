# Quick Start Guide

## CameraSpoof - 5 Minute Setup

### Prerequisites (5 minutes)
1. ✅ Rooted Android device (10-15)
2. ✅ Magisk installed
3. ✅ LSPosed installed via Magisk
4. ✅ Device rebooted after LSPosed install

### Installation (2 minutes)

#### Step 1: Install APK
```bash
# Download from releases
# Install: adb install app-release.apk
# Or install manually on device
```

#### Step 2: Activate Module
1. Open **LSPosed Manager**
2. Go to **Modules** tab
3. Enable **CameraSpoof** ✅
4. Tap on CameraSpoof
5. Add **System Framework (android)** to scope ✅

#### Step 3: Reboot
```bash
# Reboot device
adb reboot
```

### Configuration (3 minutes)

#### Step 1: Open CameraSpoof App
1. Grant storage permissions when prompted
2. Wait for app list to load

#### Step 2: Configure Target App
1. Search for your target app (e.g., "Instagram")
2. Toggle **ON** the switch
3. Tap **Select Image**
4. Choose a photo from gallery
5. Select spoof mode:
   - **Front Camera** - Selfie camera only
   - **Back Camera** - Rear camera only
   - **All Cameras** - Both cameras

#### Step 3: Test
1. Open target app
2. Use camera feature
3. Your selected image should appear! 🎉

### Verification

#### Check Module Status
```
LSPosed Manager → Modules → CameraSpoof
✅ Enabled
✅ Scope: System Framework
✅ Active
```

#### Check Logs
```
CameraSpoof App → Logs Icon (top right)
✅ Hook success messages
✅ Package name appears
✅ Image injection logs
```

### Troubleshooting (If not working)

#### Module Not Active
```bash
# 1. Check LSPosed
LSPosed Manager → Modules → Verify enabled

# 2. Check Scope
LSPosed Manager → CameraSpoof → Scope → Add "android"

# 3. Reboot
adb reboot
```

#### Image Not Appearing
```bash
# 1. Verify image selected
CameraSpoof → Check image preview

# 2. Check permissions
Settings → Apps → CameraSpoof → Permissions → Storage ✅

# 3. Check logs
CameraSpoof → Logs → Look for errors
```

#### App Crashes
```bash
# 1. Try different image
# 2. Use smaller image
# 3. Check logcat
adb logcat | grep CameraSpoof
```

### Common Use Cases

#### Social Media
```
Instagram → Enable → Select photo → Front Camera
Snapchat → Enable → Select photo → All Cameras
WhatsApp → Enable → Select photo → Back Camera
```

#### Video Calls
```
Zoom → Enable → Select photo → Front Camera
Teams → Enable → Select photo → Front Camera
```

#### Games
```
Pokemon GO → Enable → Select photo → Back Camera
```

### Tips & Tricks

#### Best Practices
- ✅ Use high-quality images (1920x1080)
- ✅ Match image orientation to camera
- ✅ Test in safe environment first
- ✅ Keep logs for debugging
- ✅ Export logs before clearing

#### Performance
- 📉 Smaller images = faster processing
- 📉 Fewer enabled apps = better performance
- 📉 Clear old logs regularly

#### Safety
- ⚠️ Only use on apps you trust
- ⚠️ Respect app terms of service
- ⚠️ Use ethically and legally
- ⚠️ Educational purposes only

### Advanced Configuration

#### Multiple Apps
```
App 1 → Image A → Front Camera
App 2 → Image B → Back Camera
App 3 → Image C → All Cameras
```

#### Changing Images
```
1. Open CameraSpoof
2. Find app
3. Tap "Change Image"
4. Select new photo
5. Done! (No reboot needed)
```

#### Disabling
```
1. Toggle OFF in CameraSpoof
2. Or disable module in LSPosed
3. Or uninstall app
```

### Build from Source (Optional)

```bash
# Clone repository
git clone https://github.com/yourusername/CameraSpoof.git
cd CameraSpoof

# Build
chmod +x gradlew
./gradlew assembleRelease

# Install
adb install app/build/outputs/apk/release/app-release.apk
```

### Getting Help

#### Documentation
- 📖 [README.md](README.md) - Overview
- 📖 [INSTALLATION.md](INSTALLATION.md) - Detailed install
- 📖 [FAQ.md](FAQ.md) - Common questions
- 📖 [DEVELOPMENT.md](DEVELOPMENT.md) - Technical details

#### Support
- 🐛 [GitHub Issues](../../issues) - Bug reports
- 💬 [GitHub Discussions](../../discussions) - Questions
- 📚 [Wiki](../../wiki) - Documentation

### Success Checklist

Before reporting issues, verify:
- ✅ LSPosed installed and active
- ✅ CameraSpoof module enabled
- ✅ System Framework in scope
- ✅ Device rebooted after activation
- ✅ Storage permissions granted
- ✅ Image selected for target app
- ✅ App toggle is ON
- ✅ Logs show hook activity

### Next Steps

1. ✅ Module working? Great!
2. 📱 Configure more apps
3. 🎨 Try different images
4. 📊 Monitor logs
5. ⭐ Star the repo if you like it!

---

**Total Setup Time**: ~10 minutes

**Difficulty**: Easy (if you have LSPosed)

**Success Rate**: 95%+ (with proper setup)

Enjoy CameraSpoof! 🎉
