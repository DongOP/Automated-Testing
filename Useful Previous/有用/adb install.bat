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
echo ################   安装开始...    ################
goto MAIN


:MAIN
rem search all the apks in the PATH.
rem 首先请设置需要安装的apk文件所在的路径,
rem 程序会自动搜索该文件夹(包括子目录)下的所有的apk文件，
set work_path=C:\Users\Lenovo\Desktop\Test

cd /d %work_path%
for /R %%s in (*.apk) do (
echo 正在安装 %%s
adb install "%%s"
)


:End
echo.
echo ################  安装结束!       ################
pause

