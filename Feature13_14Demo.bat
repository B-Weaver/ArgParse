cd demos

javac -cp .;..\build\libs\ArgParse-1.0.jar PizzaBuilderB.java
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar PizzaBuilderB 19 3 5 --cheese motzorella -d hand-tossed
pause
cls

java -cp .;..\build\libs\ArgParse-1.0.jar PizzaBuilderB 19 3 5 --cheese motzorella
pause
cls