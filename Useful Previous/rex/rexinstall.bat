adb root

pause

adb remount

adb shell rm /system/priv-app/SystemUI.apk
adb shell rm /system/priv-app/SystemUI.odex
adb push JuiSystemUI.apk /system/priv-app/JuiSystemUI.apk

adb shell rm /system/priv-app/Settings.apk
adb shell rm /system/priv-app/Settings.odex
adb push JuiSettings.apk /system/priv-app/JuiSettings.apk


adb shell rm /system/priv-app/Keyguard.apk
adb shell rm /system/priv-app/Keyguard.odex
adb push JuiKeyguard.apk /system/priv-app/JuiKeyguard.apk



adb install -r JuiFileManager.apk




pause