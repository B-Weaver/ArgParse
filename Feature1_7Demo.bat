cd demos

javac -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculator.java
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculator 7
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculator 7 5
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculator 7 5 2
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculator 7 5 2 89
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculator -h
pause

javac -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorB.java
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorB 7 5 2 --digits 1 --type ellipsoid
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorB 7 5 2 --digits 1
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorB 7 5 2
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorB 7 5 2 --help
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorB 7 5 2 -d 1 -t ellipsoid
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorB 7 5 2 -t ellipsoid
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorB 7 5 2 -d 5
pause