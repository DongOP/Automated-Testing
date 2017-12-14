@echo off

rem 重复执行某操作的模板（并打印执行次数）

rem 要循环多少次
set Dong_CNT=10

:REPEAT_FRAG

set /a Dong_CNT=%Dong_CNT%-1

if %Dong_CNT% EQU 0 goto REPEAT_OUT



rem 这里写你循环的代码，记得不要在里面写goto
rem 默认打印出次数，删掉它把
echo %Dong_CNT%




rem 你的代码不要写到我的下面

goto REPEAT_FRAG

:REPEAT_OUT
rem exit