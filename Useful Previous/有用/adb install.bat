@echo off
title ADB Install Apks[By Dong]
goto BEGIN

:COMMENT
echo ##############################################
rem 	adb_install_apks.bat
rem			By Dong @2015/01/16
rem
rem 	Description:
rem 		Used to install all the apks in system/app.
rem
rem 	Version: 2.0
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
set work_path=C:\Users\Lenovo\Desktop\Test

cd /d %work_path%
for /R %%s in (*.apk) do (
echo ���ڰ�װ %%s
adb install "%%s"
)


:End
echo.
echo ################  ��װ����!       ################
pause

