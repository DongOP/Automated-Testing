@echo off&setlocal enabledelayedexpansion title adb uninstall [by Dong] 

rem ����adb·�� 
rem set adb_path=C:\Program Files\Wandoujia  
set adb_path=C:\Users\Lenovo

rem adb.exe�����жϼ����� 
if exist %adb_path%\adb.exe (goto :handle) else (echo abd.exe·������ȷ����������&goto :end) 
 
rem adb.exe����ʱ�Ĵ��� 
:handle 
 
rem path����adb·�� 
set path=%adb_path%;%path% 
echo.
echo ################   ж�ؿ�ʼ...    ################
echo ע������ 
echo �뽫��Ҫж�ص�apk������д��ͬĿ¼��package.txt�� 
 
rem package.txt�����жϼ����� 
if exist package.txt ( 

rem �����ļ����� 
 for /f %%l in (package.txt) do ( 
    set /a num+=1 
    echo.&echo ж��"%%l"... 
    call adb uninstall %%l 
 ) 
) else ( 
  echo.&echo package.txt�����ڣ� 
) 
 
:end 
echo.
echo ################   ж�ؽ���!      ################
pause 
