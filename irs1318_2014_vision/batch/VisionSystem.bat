set JAVA_HOME=C:/Java/jdk1.6.0_30
set LD_LIBRARY_PATH=C:/opencv/jni

set JAVA_EXE = %JAVA_HOME%/bin/java.exe
set CLASSPATH=C:/DEV/Workspace/VisionSystem/lib/*;C:/DEV/Workspace/VisionSystem/dist/irs1318/libraries/jar/VisionSystem.jar

%JAVA_HOME%/bin/java.exe org.usfirst.frc1318.vision.VisionSystem 1>> c:\logs\visionSystem.log 2>&1
