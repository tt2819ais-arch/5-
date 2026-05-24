# Security Policy

## Supported Versions

| Version | Supported          |
| ------- | ------------------ |
| 1.0.x   | :white_check_mark: |

## Reporting a Vulnerability

If you discover a security vulnerability in CameraSpoof, please report it responsibly:

1. **Do NOT** open a public issue
2. Email the maintainers directly (if available)
3. Or use GitHub's private vulnerability reporting feature

### What to Include

- Description of the vulnerability
- Steps to reproduce
- Potential impact
- Suggested fix (if any)

### Response Time

- Initial response: Within 48 hours
- Status update: Within 7 days
- Fix timeline: Depends on severity

## Security Considerations

### Module Permissions

CameraSpoof requires:
- Storage permissions (for image selection)
- Query all packages (for app list)
- World-readable SharedPreferences (for Xposed communication)

### Data Storage

- Images stored in app private directory
- Database encrypted at rest (Android default)
- No network communication
- No data collection or telemetry

### Xposed Integration

- Module only hooks selected apps
- Graceful error handling
- No system camera modification
- Fallback to original behavior on errors

### Known Limitations

- Requires root access (LSPosed/Xposed)
- World-readable SharedPreferences (required for Xposed)
- Images stored unencrypted in app directory

## Best Practices

### For Users

1. Only enable spoofing for trusted apps
2. Review selected images before use
3. Keep module updated
4. Monitor logs for unexpected behavior
5. Use strong device encryption

### For Developers

1. Validate all user inputs
2. Handle errors gracefully
3. Minimize permissions
4. Follow secure coding practices
5. Regular security audits

## Responsible Disclosure

We appreciate security researchers who:
- Report vulnerabilities privately
- Allow reasonable time for fixes
- Don't exploit vulnerabilities
- Help improve security

## Legal Notice

This module is for educational purposes only. Users are responsible for:
- Complying with local laws
- Respecting app terms of service
- Using the module ethically
- Understanding security implications

## Updates

Security updates will be released as soon as possible after verification.
Users will be notified through:
- GitHub releases
- Security advisories
- README updates
