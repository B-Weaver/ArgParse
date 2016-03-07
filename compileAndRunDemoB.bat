cd demos

javac -cp .;..\build\libs\ArgParse-1.0.jar Feature8Demo.java
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar Feature8Demo 7 --myArgs myVal 3 2
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar Feature8Demo 7 something 2
pause
cls