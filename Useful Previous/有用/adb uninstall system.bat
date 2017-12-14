@echo off
title ADB Uninstall[by Dong]



rem    Ð¶ÔØsystem/appÏÂµÄapk
rem 	   Version: 2.0

@echo on

	adb root
pause

	adb remount
	
    adb shell rm /system/app/JuiBrowser.apk
    adb shell rm /system/app/JuiFileManager.apk	
    adb shell rm /system/app/padasf.apk
	adb shell rm /system/app/PadaGameCenter_v3.03.10_20150113.apk
	adb shell rm /system/app/JuiUserFeedback.apk
	adb shell rm /system/app/JuiCalculator.apk
	adb shell rm /system/app/JuiDeskClock.apk
	adb shell rm /system/app/JuiWallpaperLibrary.apk
	adb shell rm /system/app/JuiThemeLibrary.apk
	
rem ###############  Ð¶ÔØ½áÊø!       ###############
pause
