FROM alpine:latest AS build

ARG JAR_FILE=project/application/build/libs/application-0.0.1-SNAPSHOT.jar

# Datadog APMs
RUN if [ "$(uname -m)" != "aarch64" ] ; then \
      apk add wget && \
      wget -O /dd-java-agent.jar https://dtdg.co/latest-java-tracer ; \
    else \
      touch /dd-java-agent.jar ; fi ;

RUN apk add unzip
COPY ${JAR_FILE} /main.jar
RUN unzip main.jar

FROM gcr.io/distroless/java17-debian11

WORKDIR /app

ARG CLASS="com.smarttrade.demo.SmartTradeDemoApplication"

ENV HOST="0.0.0.0"
ENV SPRING_PROFILES_ACTIVE=production

EXPOSE 8080

COPY --from=build /dd-java-agent.jar /app/dd-java-agent.jar
COPY --from=build /BOOT-INF/lib/ /app/lib/
COPY --from=build /BOOT-INF/classes/ /app/classes/
COPY --from=build /META-INF/ /app/classes/META-INF/

ENTRYPOINT [ "java", "-XX:NativeMemoryTracking=summary", "--enable-preview", "-cp", "/app/lib/*:/app/classes"]
CMD ["com.smarttrade.demo.SmartTradeDemoApplication"]
