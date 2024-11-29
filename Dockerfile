FROM amazoncorretto:21-alpine-jdk as builder

WORKDIR /app

COPY ./build.gradle .
COPY ./settings.gradle .
COPY ./gradlew .
COPY ./gradle ./gradle

RUN ./gradlew clean

COPY ./src ./src

RUN ./gradlew build -x test

FROM amazoncorretto:21-alpine-jdk

ARG BUILD_ARCHIVE=/app/build/libs/

WORKDIR /app

COPY --from=builder $BUILD_ARCHIVE/msvc_franchise-0.0.1-SNAPSHOT.jar .

ENV PORT=8080

EXPOSE $PORT

CMD ["java", "-jar", "msvc_franchise-0.0.1-SNAPSHOT.jar"]