
FROM java:8  
COPY / /
#WORKDIR /
RUN ls -l
RUN pwd
RUN whoami
WORKDIR /usr/share/tomcat8/.jenkins/workspace/sendx/target/
RUN pwd
RUN ls -l
#RUN chmod 777 *
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]



