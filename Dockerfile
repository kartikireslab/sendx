
FROM java:8  
COPY / /
WORKDIR /
RUN pwd
RUN whoami
RUN su ireslab04 `qwerty-123`
RUN whoami
RUN cd /usr/share/tomcat8/.jenkins/workspace/sendx/target/
RUN chmod 777 *
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]



