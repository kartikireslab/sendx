
FROM java:8  
COPY / /
#WORKDIR /
WORKDIR /usr/share/tomcat8/.jenkins/workspace/sendx/target/
RUN pwd
RUN ls
RUN chmod 777 *
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]



