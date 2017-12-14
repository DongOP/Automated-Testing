@echo off
title ADB Uninstall[by Dong]



rem    删除system/app和system/priv-app下指定的apk
rem 	   Version: 2.1

@echo on

	adb root
pause

	adb remount
	
    adb shell rm /system/app/JuiBrowser.apk
    adb shell rm /system/app/JuiFileManager.apk	
    adb shell rm /system/app/padasf.apk
	adb shell rm /system/app/JuiGameCenter.apk
	adb shell rm /system/app/JuiUserFeedback.apk
	adb shell rm /system/app/JuiCalculator.apk
	adb shell rm /system/app/JuiDeskClock.apk
	adb shell rm /system/app/JuiWallpaperLibrary.apk
	adb shell rm /system/app/JuiThemeLibrary.apk
	adb shell rm /system/priv-app/JuiLauncher.apk
	
rem ###############  卸载结束!       ###############
pause
