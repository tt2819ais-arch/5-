# Contributing to CameraSpoof

Thank you for your interest in contributing to CameraSpoof! This document provides guidelines for contributing to the project.

## Code of Conduct

- Be respectful and inclusive
- Focus on constructive feedback
- Help others learn and grow

## How to Contribute

### Reporting Bugs

1. Check if the bug has already been reported in [Issues](../../issues)
2. If not, create a new issue with:
   - Clear title and description
   - Steps to reproduce
   - Expected vs actual behavior
   - Device info (Android version, LSPosed version)
   - Relevant logs

### Suggesting Features

1. Check if the feature has been suggested in [Issues](../../issues)
2. Create a new issue with:
   - Clear description of the feature
   - Use cases and benefits
   - Potential implementation approach

### Pull Requests

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes
4. Test thoroughly
5. Commit with clear messages (`git commit -m 'Add amazing feature'`)
6. Push to your fork (`git push origin feature/amazing-feature`)
7. Open a Pull Request

### Code Style

- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Add comments for complex logic
- Keep functions small and focused
- Write unit tests when applicable

### Testing

Before submitting a PR:
- Test on multiple Android versions (10-15)
- Test with different camera apps
- Verify no crashes or ANRs
- Check logs for errors
- Test edge cases

### Commit Messages

- Use present tense ("Add feature" not "Added feature")
- Use imperative mood ("Move cursor to..." not "Moves cursor to...")
- Limit first line to 72 characters
- Reference issues and PRs when relevant

## Development Setup

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle
4. Build and run

## Project Structure

See [DEVELOPMENT.md](DEVELOPMENT.md) for detailed project structure.

## Questions?

Feel free to ask questions in [Issues](../../issues) or [Discussions](../../discussions).

## License

By contributing, you agree that your contributions will be licensed under the MIT License.
