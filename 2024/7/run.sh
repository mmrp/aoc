CLASS_PATH=$HOME/libs/java
jars=junit-platform-console-standalone-1.10.2.jar
javac -cp .:${CLASS_PATH}/${jars} Solution.java SolutionTest.java
java -jar ${CLASS_PATH}/${jars} --class-path .:${CLASS_PATH} --scan-class-path
