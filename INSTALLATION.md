# CameraSpoof - Installation Guide

## Prerequisites

Before installing CameraSpoof, ensure you have:

1. **Rooted Android Device**
   - Android 10 or higher (API 29+)
   - Magisk installed and working
   - Zygisk enabled (recommended)

2. **LSPosed Framework**
   - LSPosed installed via Magisk
   - LSPosed activated and working
   - Access to LSPosed Manager app

## Installation Steps

### Step 1: Install LSPosed

If you haven't installed LSPosed yet:

1. Download LSPosed from [GitHub Releases](https://github.com/LSPosed/LSPosed/releases)
2. Install via Magisk Manager
3. Reboot device
4. Open LSPosed Manager to verify installation

### Step 2: Install CameraSpoof

1. Download the latest `app-release.apk` from [Releases](../../releases)
2. Install the APK on your device
3. Grant storage permissions when prompted

### Step 3: Activate Module

1. Open **LSPosed Manager**
2. Go to **Modules** tab
3. Enable **CameraSpoof**
4. The module should appear with green checkmark

### Step 4: Configure Scope

1. In LSPosed Manager, tap on **CameraSpoof**
2. Go to **Scope** section
3. Enable **System Framework (android)**
4. Optionally enable specific apps you want to spoof
5. Save changes

### Step 5: Reboot

1. Reboot your device
2. Wait for system to fully boot

### Step 6: Configure Apps

1. Open **CameraSpoof** app
2. Search for the app you want to spoof
3. Toggle the switch to enable
4. Tap **Select Image** and choose a photo
5. Select spoof mode (Front/Back/All cameras)
6. Settings are saved automatically

### Step 7: Test

1. Open the target app
2. Use the camera feature
3. The selected image should appear instead of real camera
4. Check CameraSpoof logs for confirmation

## Verification

### Check Module Status

1. Open LSPosed Manager
2. CameraSpoof should show:
   - ✅ Enabled
   - ✅ Scope configured
   - ✅ Active after reboot

### Check App Status

1. Open CameraSpoof app
2. Enabled apps should show:
   - Toggle ON
   - Selected image preview
   - Chosen spoof mode

### Check Logs

1. Open CameraSpoof app
2. Tap logs icon (top right)
3. Look for hook success messages
4. Verify target package names

## Troubleshooting

### Module Not Working

**Problem**: Camera still shows real feed

**Solutions**:
1. Verify LSPosed is active
2. Check module is enabled in LSPosed
3. Ensure System Framework is in scope
4. Reboot device
5. Check logs for errors

### App Not Listed

**Problem**: Target app doesn't appear in list

**Solutions**:
1. Ensure app is installed
2. Pull down to refresh list
3. Use search function
4. Check if app is system app

### Image Not Loading

**Problem**: Selected image doesn't appear

**Solutions**:
1. Grant storage permissions
2. Select image again
3. Check image file exists
4. Try different image format
5. Check logs for errors

### Permission Denied

**Problem**: Can't select images

**Solutions**:
1. Go to Settings → Apps → CameraSpoof
2. Grant storage permissions
3. On Android 13+, grant "Photos and videos" permission
4. Restart app

### Crashes

**Problem**: App or target app crashes

**Solutions**:
1. Check logcat for stack traces
2. Clear app data
3. Reinstall module
4. Report issue with logs

## Uninstallation

### Remove Module

1. Open LSPosed Manager
2. Disable CameraSpoof module
3. Reboot device
4. Uninstall CameraSpoof app

### Clean Uninstall

1. Disable module in LSPosed
2. Open CameraSpoof app
3. Disable all apps
4. Clear app data
5. Uninstall app
6. Reboot device

## Advanced Configuration

### Multiple Images

Currently, only one image per app is supported. To change:
1. Select new image
2. Old image is automatically replaced

### Scope Configuration

For better performance:
1. Only add target apps to scope
2. System Framework is required
3. Avoid adding all apps

### Log Management

To manage logs:
1. Export logs regularly
2. Clear old logs
3. Monitor log size

## Support

For issues:
- Check [Troubleshooting](#troubleshooting) section
- Review [GitHub Issues](../../issues)
- Check [Discussions](../../discussions)
- Read [FAQ](../../wiki/FAQ)

## Updates

To update:
1. Download new APK
2. Install over existing app
3. Reboot device
4. Verify settings preserved

## Notes

- Settings persist across reboots
- Images stored in app private directory
- Logs limited to 5000 entries
- Old files cleaned automatically
- No internet connection required
