FROM ubuntu:latest

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    git && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

RUN git clone https://github.com/SaraFelix03/Animal_Shelter.git

WORKDIR /Animal_Shelter

RUN mvn clean install

CMD [ "java", "-jar", "target/Animal_Shelter-1.0-SNAPSHOT.jar" ]