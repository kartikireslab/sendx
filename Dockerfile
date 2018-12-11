FROM java:8  
#RUN setfacl -m g:docker /usr/share/tomcat8/.jenkins/workspace/Sendx/target
crash.sh
COPY /usr/share/tomcat8/.jenkins/workspace/Sendx/target/sendx-0.0.1-SNAPSHOT.jar /usr/share/tomcat8/.jenkins/workspace/Sendx/target/sendx-0.0.1-SNAPSHOT.jar
WORKDIR /usr/share/tomcat8/.jenkins/workspace/Sendx
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
