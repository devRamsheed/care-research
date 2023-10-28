FROM openjdk:11-jre-slim
WORKDIR /app
COPY build/libs/dri-careresearch-1.0.0.jar app.jar
EXPOSE 9110
ENTRYPOINT ["java","-jar","app.jar"]