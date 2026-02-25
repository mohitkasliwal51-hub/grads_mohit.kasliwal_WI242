FROM eclipse-temurin:17-jdk

WORKDIR /app

# Install curl to download postgres driver
RUN apt-get update && apt-get install -y curl

# Download PostgreSQL JDBC driver
RUN curl -O https://jdbc.postgresql.org/download/postgresql-42.7.3.jar

COPY Main.java .

# Compile inside container
RUN javac -cp postgresql-42.7.3.jar Main.java

CMD sh -c "java -cp .:postgresql-42.7.3.jar Main && tail -f /dev/null"    