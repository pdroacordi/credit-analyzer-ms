FROM openjdk:17

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo JAR para o diretório de trabalho
COPY mainapp/target/mainapp-0.0.1-SNAPSHOT.jar main-1.0.jar

# Corrigir o formato do ENTRYPOINT
ENTRYPOINT ["java", "-jar", "main-1.0.jar"]