# Imagen base con Java 17
FROM eclipse-temurin:17-jdk

# Instalar Maven dentro del contenedor
RUN apt-get update && apt-get install -y maven

# Crear carpeta de trabajo
WORKDIR /app

# Copiar el código del proyecto
COPY . .

# Construir la app con Maven (ahora usamos mvn, no ./mvnw)
RUN mvn clean package -DskipTests

# Exponer el puerto
EXPOSE 8080

# Ejecutar el jar (usa * si no sabes el nombre exacto)
CMD java -jar target/rest-coreografia-medicos-1.0.0.jar
