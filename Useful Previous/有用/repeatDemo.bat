@echo off

rem �ظ�ִ��ĳ������ģ�壨����ӡִ�д�����

rem Ҫѭ�����ٴ�
set Dong_CNT=10

:REPEAT_FRAG

set /a Dong_CNT=%Dong_CNT%-1

if %Dong_CNT% EQU 0 goto REPEAT_OUT



rem ����д��ѭ���Ĵ��룬�ǵò�Ҫ������дgoto
rem Ĭ�ϴ�ӡ��������ɾ������
echo %Dong_CNT%




rem ��Ĵ��벻Ҫд���ҵ�����

goto REPEAT_FRAG

:REPEAT_OUT
rem exit