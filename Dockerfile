FROM java:8  
RUN chmod 777 /usr/share/tomcat8/.jenkins/workspace/Sendx/target
COPY /usr/share/tomcat8/.jenkins/workspace/Sendx/target/sendx-0.0.1-SNAPSHOT.jar /usr/share/tomcat8/.jenkins/workspace/Sendx/sendx-0.0.1-SNAPSHOT.jar
WORKDIR /usr/share/tomcat8/.jenkins/workspace/Sendx
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
