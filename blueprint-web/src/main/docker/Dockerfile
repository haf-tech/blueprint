#
# Dockerfile: creates an image holding the blueprint application with Spring Boot
# 

FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp

ADD blueprint-web.jar app.jar

# touch the Jar file to update the modification time
RUN sh -c 'touch /app.jar'

ENV JAVA_OPTS=""

# To optimize tomcat startup setting /dev/urandom as source entropy
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]