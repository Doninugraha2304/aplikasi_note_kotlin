#!/bin/bash

# Script untuk build dan install aplikasi ke emulator

echo "🔨 Building Elegant Notes App..."
echo ""

# Set JAVA_HOME ke Android Studio JDK
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"

# Navigate to project directory
cd /Users/josjaya/kotlin_mobile

# Clean previous build
echo "🧹 Cleaning previous build..."
./gradlew clean

# Build debug APK
echo "📦 Building APK..."
./gradlew assembleDebug

# Check if build successful
if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Build successful!"
    echo ""
    echo "📱 Installing to emulator..."
    
    # Install to emulator
    ~/Library/Android/sdk/platform-tools/adb install -r app/build/outputs/apk/debug/app-debug.apk
    
    if [ $? -eq 0 ]; then
        echo ""
        echo "✅ Installation successful!"
        echo ""
        echo "🚀 Launching app..."
        
        # Launch the app
        ~/Library/Android/sdk/platform-tools/adb shell am start -n com.noteapp.elegant/.MainActivity
        
        echo ""
        echo "🎉 App is now running on your emulator!"
        echo ""
    else
        echo ""
        echo "❌ Installation failed!"
        echo "Please check the error messages above."
    fi
else
    echo ""
    echo "❌ Build failed!"
    echo ""
    echo "💡 Alternative: Use Android Studio"
    echo "   1. In Android Studio, click the 'Run' button (▶️)"
    echo "   2. Or press Shift + F10"
    echo ""
fi
