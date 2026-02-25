# Usamos una imagen ligera de Java 17
FROM eclipse-temurin:17-jre-alpine

# Directorio de trabajo
WORKDIR /app

# Copiamos el JAR generado (asegúrate de que la ruta coincida con tu build)
COPY target/quarkus-app/lib/ /app/lib/
COPY target/quarkus-app/*.jar /app/
COPY target/quarkus-app/app/ /app/app/
COPY target/quarkus-app/quarkus/ /app/quarkus/

# Exponemos el puerto
EXPOSE 8080

# Comando para arrancar Quarkus en modo fast-jar
CMD ["java", "-jar", "quarkus-run.jar"]