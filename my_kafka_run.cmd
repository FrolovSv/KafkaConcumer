@echo off

set CLASSPATH="e:\JavaProgect\KafkaConcumer\target\classes";

for %%i in (C:\kafka\libs\*) do (
	call :concat "%%i"        
)

set COMMAND=java -classpath %CLASSPATH% %*
%COMMAND%

:concat
IF not defined CLASSPATH (
  set CLASSPATH="%~1"
) ELSE (
  set CLASSPATH=%CLASSPATH%;"%~1"
)