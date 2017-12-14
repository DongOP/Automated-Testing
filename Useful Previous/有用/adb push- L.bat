@echo off
title ADB push Apks[By Dong]
goto BEGIN

:COMMENT
echo ##############################################
rem 	adb_push_apks.bat
rem			By Dong @2015/01/17
rem		
rem 	Description:
rem 		Used to push all the apks in system/app,
rem              or push JuiLauncher.apk in system/priv-app/.
rem 
rem 	Version: 2.1
rem
echo ##############################################
pause


:BEGIN
echo.
echo ################   ��װ��ʼ...    ################ 
goto MAIN


:MAIN
rem search all the apks in the PATH.
rem ������������Ҫ��װ��apk�ļ����ڵ�·��,
rem ������Զ��������ļ���(������Ŀ¼)�µ����е�apk�ļ���
set work_path=C:\Users\Lenovo\MytestAPKs

cd /d %work_path% 
for /R %%s in (*.apk) do (
echo ���ڰ�װ %%s 
adb push "%%s" /system/app/
)
goto EXTRA


:EXTRA
adb push JuiLauncher.apk /system/priv-app/
adb shell rm /system/app/JuiLauncher.apk

:End
echo.
echo ################  ��װ����!       ################
pause

