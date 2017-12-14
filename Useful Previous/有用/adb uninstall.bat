@echo off&setlocal enabledelayedexpansion title adb uninstall [by Dong] 

rem 定义adb路径 
rem set adb_path=C:\Program Files\Wandoujia  
set adb_path=C:\Users\Lenovo

rem adb.exe存在判断及处理 
if exist %adb_path%\adb.exe (goto :handle) else (echo abd.exe路径不正确，请修正！&goto :end) 
 
rem adb.exe存在时的处理 
:handle 
 
rem path增加adb路径 
set path=%adb_path%;%path% 
echo.
echo ################   卸载开始...    ################
echo 注意事项 
echo 请将需要卸载的apk包名，写入同目录的package.txt！ 
 
rem package.txt存在判断及处理 
if exist package.txt ( 

rem 遍历文件多行 
 for /f %%l in (package.txt) do ( 
    set /a num+=1 
    echo.&echo 卸载"%%l"... 
    call adb uninstall %%l 
 ) 
) else ( 
  echo.&echo package.txt不存在！ 
) 
 
:end 
echo.
echo ################   卸载结束!      ################
pause 
