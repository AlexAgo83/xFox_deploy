@ECHO OFF
IF /I "%PROCESSOR_ARCHITECTURE:~-2%"=="64" "%ProgramFiles(x86)%\Java\jre6\bin\java.exe" -Xms1024M -Xmx1024M -jar client.jar
IF /I "%PROCESSOR_ARCHITECTURE:~-2%"=="86" java -Xms1024M -Xmx1024M -jar client.jar
PAUSE