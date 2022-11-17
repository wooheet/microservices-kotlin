# Use maven to compile the java application.
FROM gcr.io/columbus-160105/base/maven:3.8.5-openjdk-11-slim AS build-env

# Set the working directory to /app
WORKDIR /app

# copy the pom.xml file to download dependencies
COPY build.gradle.kts ./

# download dependencies as specified in pom.xml
# building dependency layer early will speed up compile time when pom is unchanged
#RUN mvn verify --fail-never

# Copy the rest of the working directory contents into the container
COPY .git /app/.git
COPY src  /app/src

RUN rm -rf /app/build

# Compile the application.
RUN --mount=type=cache,target=/root/.m2 mvn -Dmaven.test.skip=true -Djava.version=11 package

FROM gcr.io/columbus-160105/base/ubuntu1804_jdk11:2022-09-13
MAINTAINER mj_jeong0325 mj_jeong0325@netmarble.com
RUN groupadd -r -g 2001 appuser && useradd -r -u 1001 -g appuser appuser
RUN mkdir /home/appuser && chown appuser /home/appuser
USER appuser

# Copy the compiled files over.
COPY --chown=appuser:appuser ./ObjectSizer.jar /home/appuser/ObjectSizer.jar
COPY --chown=appuser:appuser --from=build-env /app/target/*shaded.jar /home/appuser/app.jar

#VOLUME /tmp
#ARG JAR_FILE
#ADD ${JAR_FILE} app.jar
#RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENV JAVA_GCLOG_OPTS=""
ENV SPRING_PROFILE="docker-local"

ENTRYPOINT exec java $JAVA_OPTS $JAVA_GCLOG_OPTS -Dspring.profiles.active=$SPRING_PROFILE -Djava.security.egd=file:/dev/./urandom -javaagent:/home/appuser/ObjectSizer.jar -jar /home/appuser/app.jar