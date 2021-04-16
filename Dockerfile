# Prepare runtime.
FROM openjdk:8 
EXPOSE 8001

# Prepare build workspace.
FROM gradle:6.8.3 AS sdk

# Setup build workspace.
USER root
RUN chown -R gradle .
USER gradle

# Copy.
COPY build.gradle .
COPY gradle/wrapper/gradle-wrapper.properties .
COPY src ./src

RUN rm -rf build

# Build, Test and publish.
RUN gradle clean bootJar

RUN ls -l build/libs/

# App image.
FROM openjdk:8 
ADD build/libs/consumer-0.0.1-SNAPSHOT.jar ./consumer.jar
ENTRYPOINT ["java", "-jar", "consumer.jar"]

CMD ["java","-jar","consumer.jar"]

