@echo off

rem Ҫѭ���Ĵ���
set Dong_CNT=10

:REPEAT_FRAG

set /a Dong_CNT=%Dong_CNT%-1

if %Dong_CNT% EQU 0 goto REPEAT_OUT

rem ��Ҫ��װ��apk�ļ����ڵ�·��
set work_path=C:\Users\Lenovo\MytestAPKs

cd /d %work_path% 

rem ��װapk
adb install JuiUserFeedback.apk

rem ж��apk
adb uninstall com.jui.feedback

rem ��ӡ����
echo %Dong_CNT%



goto REPEAT_FRAG

:REPEAT_OUT
rem exit