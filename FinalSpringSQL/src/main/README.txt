'Final' edition of the basic app is in FinalSpringSQL. It connects to our database, and has some functionality.

To run:

mvn clean package -U

Place JAR in: /usr/share/tomcat/webapps

ssh to server

Kill processes with: fuser -k 9001/tcp 

tmux

cd /usr/share/tomcat/webapps

java -jar "jarname"

ctrl+d :detach

