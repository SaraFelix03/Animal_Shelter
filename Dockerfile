FROM ubuntu:latest

# System update, installs dependencies
RUN apt-get update && apt-get install -y \
    wget \
    maven \
    git && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Install Amazon-Corretto-jdk
RUN wget -O /tmp/corretto-17.deb https://corretto.aws/downloads/latest/amazon-corretto-17-x64-linux-jdk.deb && \
    dpkg -i /tmp/corretto-17.deb && \
    rm -f /tmp/corretto-17.deb

ENV JAVA_HOME=/usr/lib/jvm/java-17-amazon-corretto
ENV PATH="$JAVA_HOME/bin:$PATH"

# Clone repo
RUN git clone https://github.com/SaraFelix03/Animal_Shelter.git

# Set working directory
WORKDIR /Animal_Shelter

# Compile the project
RUN mvn clean install

CMD ["java", "-jar", "target/Animal_Shelter-1.0-SNAPSHOT.jar"]