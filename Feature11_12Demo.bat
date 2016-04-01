cd demos

javac -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorD.java
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorD 7 5 2 -t rectangle --digits 8
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar VolumeCalculatorD 7 5 2 --type ellipsoid -d 8
pause
cls

javac -cp .;..\build\libs\ArgParse-1.0.jar PizzaBuilder.java
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar PizzaBuilder 18 2 4 --cheese motzorella -d hand-tossed
pause
cls