FROM gradle:7.5-jdk-alpine

WORKDIR /app

COPY build.gradle.kts ./

COPY .git /app/.git
COPY src  /app/src

RUN gradle clean build --no-daemon

FROM gcr.io/columbus-160105/base/ubuntu1804_jdk11:2022-09-13
MAINTAINER woohaewon woohaewon@netmarble.com
RUN groupadd -r -g 2001 appuser && useradd -r -u 1001 -g appuser appuser
RUN mkdir /home/appuser && chown appuser /home/appuser
USER appuser

# Copy the compiled files over.
COPY --chown=appuser:appuser ./ObjectSizer.jar /home/appuser/ObjectSizer.jar
COPY --chown=appuser:appuser --from=build-env /app/target/*shaded.jar /home/appuser/app.jar


ENV JAVA_OPTS=""
ENV JAVA_GCLOG_OPTS=""
ENV SPRING_PROFILE="docker-local"

ENTRYPOINT exec java $JAVA_OPTS $JAVA_GCLOG_OPTS -Dspring.profiles.active=$SPRING_PROFILE -Djava.security.egd=file:/dev/./urandom -javaagent:/home/appuser/ObjectSizer.jar -jar /home/appuser/app.jar