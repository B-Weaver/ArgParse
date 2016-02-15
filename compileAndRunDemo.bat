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