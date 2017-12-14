echo "Scanning...about two seconds"
call :wait_ext1 2
echo "2 seconds waited...."
goto :eof

:wait_ext1
@ping 127.0.0.1 -n 2 -w 1000 > nul
@ping 127.0.0.1 -n %1% -w 1000> nul