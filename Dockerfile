
FROM java:8
WORKDIR /usr/share/tomcat8/.jenkins/workspace/sendx/target/
RUN pwd
RUN ls
RUN su - ireslab04 <<!
'qwerty-123'
RUN chmod -R 777 .
RUN java -jar sendx-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
