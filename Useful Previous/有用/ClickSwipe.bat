@echo off

rem 要循环多少次
set Dong_CNT=10

:REPEAT_FRAG

set /a Dong_CNT=%Dong_CNT%-1

if %Dong_CNT% EQU 0 goto REPEAT_OUT

adb shell input tap 600 1160
adb shell input keyevent KEYCODE_HOME

echo %Dong_CNT%

goto REPEAT_FRAG

:REPEAT_OUT
rem exit