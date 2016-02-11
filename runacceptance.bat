cd acceptance

javac -cp .;..\build\classes\main ArgParserKeywords.java
java -cp .;..\build\classes\main;C:\RobotFramework\robotframework-2.9.jar org.robotframework.RobotFramework ArgParserTest.txt
cd ..
pause