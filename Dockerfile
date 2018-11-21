
FROM java:8
WORKDIR /usr/share/tomcat8/.jenkins/workspace/sendx/target/
RUN pwd
RUN ls
RUN sudo su - ireslab04 <<! >/dev/null 2>&1
RUN chmod -R 777 .
RUN java -jar sendx-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
