cd demos

javac -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorD.java
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorD 7 5 2 -t rectangle --digits 8
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorD 7 5 2 --type ellipsoid -d 8
pause