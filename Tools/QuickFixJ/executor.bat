@echo off
 
REM Scripts for the QFJ source distribution (Java 5+)
 
if "%OS%"=="Windows_NT" @setlocal
if "%OS%"=="WINNT" @setlocal
 
set CURRENTDIR=%~dp0
 
set CP=%CURRENTDIR%jars\*
 
java -cp %CP% quickfix.examples.executor.Executor %1
