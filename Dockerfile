FROM jenkins/jenkins:lts

USER root

RUN apt-get update && \
apt-get -y install apt-transport-https \
    ca-certificates \
    curl \
    gnupg2 \
    software-properties-common && \
curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$docker030303")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
add-apt-repository \
    "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$docker030303") \
    $(lsb_release -cs) \
    stable" && \
apt-get update && \
apt-get -y install docker-ce

RUN apt-get install -y docker-ce

RUN usermod -a -G docker jenkins

USER jenkins


FROM java:8  
COPY /target/sendx-api-0.0.1-SNAPSHOT.war /target/sendx-api-0.0.1-SNAPSHOT.war
WORKDIR server/target
CMD ["java","-jar","/target/sendx-api-0.0.1-SNAPSHOT.war"]