# CameraSpoof - FAQ

## General Questions

### What is CameraSpoof?

CameraSpoof is an LSPosed/Xposed module that allows you to replace camera images in selected apps with custom photos from your gallery.

### Is it safe to use?

The module is designed with safety in mind:
- Only affects selected apps
- Does not modify system camera
- Graceful error handling
- No bootloop risk
- Open source code

However, use at your own risk and only on apps you trust.

### Is root required?

Yes, CameraSpoof requires:
- Rooted device
- LSPosed or Xposed Framework
- Magisk (recommended)

### Which Android versions are supported?

- Android 10 (API 29)
- Android 11 (API 30)
- Android 12/12L (API 31-32)
- Android 13 (API 33)
- Android 14 (API 34)
- Android 15 (API 35)

## Installation

### How do I install CameraSpoof?

See the detailed [INSTALLATION.md](INSTALLATION.md) guide.

### Do I need LSPosed or Xposed?

You need either:
- LSPosed (recommended, modern)
- Xposed Framework (legacy)

LSPosed is recommended for Android 10+.

### Why isn't the module showing in LSPosed?

1. Check if APK is installed
2. Verify LSPosed is active
3. Reboot device
4. Check LSPosed logs

### Do I need to add apps to scope?

Yes, you must add:
- System Framework (android) - Required
- Target apps (optional, for better targeting)

## Usage

### How do I spoof an app?

1. Open CameraSpoof
2. Find the app
3. Toggle enable
4. Select image
5. Choose camera mode
6. Open target app

### Can I use different images for different apps?

Yes, each app has its own configuration with separate image selection.

### What are the spoof modes?

- **Front Camera**: Only spoof front-facing camera
- **Back Camera**: Only spoof rear camera
- **All Cameras**: Spoof all cameras

### Can I spoof video?

Currently, only still images are supported. Video spoofing may be added in future versions.

### How do I change the image?

1. Open CameraSpoof
2. Find the app
3. Tap "Change Image"
4. Select new photo

### How do I disable spoofing?

Toggle off the switch for that app in CameraSpoof.

## Troubleshooting

### Camera still shows real feed

**Possible causes**:
1. Module not activated in LSPosed
2. System Framework not in scope
3. Device not rebooted after activation
4. Image not selected
5. App using custom camera API

**Solutions**:
1. Check LSPosed activation
2. Add System Framework to scope
3. Reboot device
4. Select image again
5. Check logs for errors

### App crashes when using camera

**Possible causes**:
1. Incompatible camera API
2. Image format issue
3. Memory constraints

**Solutions**:
1. Try different image
2. Use smaller image
3. Check logcat
4. Report issue with logs

### Selected image doesn't appear

**Possible causes**:
1. Image file deleted
2. Permission denied
3. Hook not working

**Solutions**:
1. Select image again
2. Grant storage permissions
3. Check logs
4. Verify module is active

### "Permission denied" when selecting image

**Solutions**:
1. Go to Settings → Apps → CameraSpoof
2. Grant storage permissions
3. On Android 13+: Grant "Photos and videos"
4. Restart app

### Module not working after update

**Solutions**:
1. Clear app data
2. Reconfigure apps
3. Reboot device
4. Reinstall if needed

### Logs show no activity

**Possible causes**:
1. Module not hooked
2. App not in scope
3. Camera not used

**Solutions**:
1. Verify LSPosed activation
2. Check scope configuration
3. Use camera in target app
4. Check LSPosed logs

## Compatibility

### Does it work with all camera apps?

Most apps using standard Android Camera APIs are supported. Apps with heavily customized camera implementations may not work.

### Does it work with social media apps?

Generally yes, but results vary:
- ✅ Instagram
- ✅ Snapchat
- ✅ WhatsApp
- ✅ Telegram
- ⚠️ Some apps may detect spoofing

### Does it work with banking apps?

Some banking apps have anti-tampering detection. Use with caution and at your own risk.

### Does it work with games?

Depends on the game's camera implementation. Most should work.

## Performance

### Does it affect battery life?

Minimal impact. The module only activates when camera is used in hooked apps.

### Does it slow down the camera?

There may be slight delay during image processing, depending on:
- Image size
- Device performance
- Camera API used

### How much storage does it use?

- App: ~10-20 MB
- Images: Depends on number and size
- Logs: Limited to 5000 entries
- Database: Minimal

## Privacy & Security

### Does it collect data?

No. CameraSpoof:
- No internet permission
- No analytics
- No telemetry
- All data stored locally

### Is my data safe?

- Images stored in app private directory
- Database encrypted (Android default)
- No cloud sync
- No external access

### Can apps detect spoofing?

Sophisticated apps may detect:
- Xposed/LSPosed presence
- Modified camera behavior
- Inconsistent metadata

Use responsibly and ethically.

### Is it legal?

Legality depends on:
- Your jurisdiction
- How you use it
- Target app's terms of service

Use for educational purposes only.

## Development

### Is it open source?

Yes, the code is available on GitHub under MIT License.

### Can I contribute?

Yes! See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

### How do I report bugs?

1. Check existing [Issues](../../issues)
2. Create new issue with:
   - Device info
   - Android version
   - LSPosed version
   - Steps to reproduce
   - Logs

### How do I request features?

Create a feature request in [Issues](../../issues) with:
- Clear description
- Use cases
- Expected behavior

## Advanced

### Can I use multiple images per app?

Currently no. Only one image per app is supported.

### Can I automate image rotation?

Not currently supported. May be added in future.

### Can I spoof camera metadata?

No, only the image itself is spoofed.

### Can I use it programmatically?

The module is designed for manual configuration through the UI.

### How does it work internally?

CameraSpoof hooks Android Camera APIs (Camera1 and Camera2) and intercepts image capture calls, replacing the real camera data with your selected image.

## Support

### Where can I get help?

- Read documentation
- Check [Issues](../../issues)
- Join [Discussions](../../discussions)
- Review [Wiki](../../wiki)

### How do I contact developers?

- GitHub Issues (bugs)
- GitHub Discussions (questions)
- Pull Requests (contributions)

### Is there a community?

Check GitHub Discussions for community support and discussions.

## Updates

### How do I update?

1. Download new APK
2. Install over existing
3. Reboot device
4. Settings preserved

### Will updates break my config?

No, settings are preserved across updates.

### How often are updates released?

Updates are released as needed for:
- Bug fixes
- New features
- Security patches
- Compatibility updates

## Miscellaneous

### Why "CameraSpoof"?

The name clearly describes what it does - spoofs camera images.

### Who made this?

CameraSpoof is an open source project. See contributors on GitHub.

### Can I use it commercially?

See LICENSE file for terms. Generally for educational use only.

### Does it work offline?

Yes, no internet connection required.

### Can I backup my settings?

Settings are stored in app data. Use Android backup or manually export database.

---

**Still have questions?** Open an issue on [GitHub](../../issues)!
