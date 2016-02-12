cd demos

javac -cp .;..\build\libs\ArgParse-1.0.jar BoxVolumeCalculator.java
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar BoxVolumeCalculator 7 5 2
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar BoxVolumeCalculator 7 5 2 89
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar BoxVolumeCalculator -h


pause
