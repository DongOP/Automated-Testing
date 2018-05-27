@echo off

rem 要循环的次数
set Dong_CNT=10

:REPEAT_FRAG

set /a Dong_CNT=%Dong_CNT%-1

if %Dong_CNT% EQU 0 goto REPEAT_OUT

rem 需要安装的apk文件所在的路径
set work_path=C:\Users\Lenovo\MytestAPKs

cd /d %work_path% 

rem 安装apk
adb install JuiUserFeedback.apk

rem 卸载apk
adb uninstall com.jui.feedback

rem 打印次数
echo %Dong_CNT%

goto REPEAT_FRAG

:REPEAT_OUT
rem exit