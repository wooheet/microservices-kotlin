#FROM adoptopenjdk:11-jdk-hotspot AS builder
#
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle.kts .
#COPY settings.gradle.kts .
#COPY src src
#
#RUN chmod +x ./gradlew
#RUN ./gradlew bootJar
#
#FROM adoptopenjdk:11-jdk-hotspot
#ARG JAR_FILE=build/libs/*.jar
#ARG ENVIRONMENT
#
#COPY --from=builder ${JAR_FILE} app.jar
#
#ENV SPRING_PROFILES_ACTIVE=$P{ENVIRONMENT}
#
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app.jar"]


### build stage ###
FROM adoptopenjdk:11-jdk-hotspot AS builder

# set arg
ARG WORKSPACE=/home/app
ARG BUILD_TARGET=${WORKSPACE}/build/libs
WORKDIR ${WORKSPACE}

# copy code & build
COPY . .
RUN ./gradlew clean bootJar

# unpack jar
WORKDIR ${BUILD_TARGET}
RUN jar -xf *.jar


### create image stage ###
FROM adoptopenjdk:11-jdk-hotspot

ARG WORKSPACE=/home/app
ARG BUILD_TARGET=${WORKSPACE}/build/libs
ARG DEPLOY_PATH=${WORKSPACE}/deploy

# copy from build stage
COPY --from=builder ${BUILD_TARGET}/org ${DEPLOY_PATH}/org
COPY --from=builder ${BUILD_TARGET}/BOOT-INF/lib ${DEPLOY_PATH}/BOOT-INF/lib
COPY --from=builder ${BUILD_TARGET}/META-INF ${DEPLOY_PATH}/META-INF
COPY --from=builder ${BUILD_TARGET}/BOOT-INF/classes ${DEPLOY_PATH}/BOOT-INF/classes

WORKDIR ${DEPLOY_PATH}

#EXPOSE 9099/tcp
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]