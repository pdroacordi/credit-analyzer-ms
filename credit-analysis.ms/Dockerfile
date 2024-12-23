FROM openjdk:17

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo JAR para o diretório de trabalho
COPY credit-analysis.ms/target/credit-analysis-ms-0.0.1-SNAPSHOT.jar credit-analysis-1.0.jar

# Corrigir o formato do ENTRYPOINT
ENTRYPOINT ["java", "-jar", "credit-analysis-1.0.jar"]