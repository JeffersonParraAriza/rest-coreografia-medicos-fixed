# Imagen base con Java 17
FROM eclipse-temurin:17-jdk

# Crear carpeta de trabajo
WORKDIR /app

# Copiar el código del proyecto
COPY . .

# Construir la app con Maven
RUN ./mvnw clean package -DskipTests

# Exponer el puerto (Render asignará dinámicamente su propio, no importa el 8080)
EXPOSE 8080

# Ejecutar el JAR
CMD ["java", "-jar", "target/rest-coreografia-medicos-fixed-0.0.1-SNAPSHOT.jar"]
