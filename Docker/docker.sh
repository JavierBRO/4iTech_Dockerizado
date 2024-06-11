


#!/bin/bash

docker --version

# Imprimir contenedores en ejecución:
docker ps

# Imprimir todos los contenedores en ejecución y detenidos:
docker ps -a

# Comprobar si la instalación es correcta:
docker run hello-world

# Crear un contenedor MySQL
# hub.docker.com

docker pull mysql:8.3.0

# -p 3307:3306 aquí el 3307 indica el puerto del host y el 3306 el puerto del contenedor
# Se ejecuta solo la primera vez para crearlo
docker run --name mysql-830 -p 3307:3306 -e MYSQL_ROOT_PASSWORD=admin -d mysql:8.3.0

# Parar el contenedor:
docker stop mysql-830

# Volver a iniciar el contenedor
docker start mysql-830

# Entrar dentro del contenedor: docker exec -it mysql-830 cmd
docker exec -it mysql-830 bash

# Entrar a mysql por terminal:
mysql -u root -p

# Ver bases de datos:
show databases;

# Salir de mysql y del contenedor...con exit a secas sin ; tambien sale
exit;

# Descargar imagen java:
docker pull eclipse-temurin:21-jre-jammy



# MONTAR APLICACION FULL STACK ANGULAR Y SPRING CON MYSQL Y CON DOCKER:

# 1. Crear red Docker
docker network create myapp-network

# 2. Crear base de datos MySQL conectada a docker network:
docker stop mysql-830
docker rm mysql-830
docker run --name mysql-830 -p 3307:3306 -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=backend_db --network myapp-network -d mysql:8.3.0

# 3. Crear imagen backend
docker build -t backend:0.0.1 .

# 4. Crear el contenedor a partir de la imagen backend
docker run -p 8080:8080 --name backend --network myapp-network -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-830:3306/backend_db -d -t backend:0.0.1

# 5. Ver logs de spring (Ctrl + C para salir)
docker logs -f backend

# 6.0 Construir la aplicación Angular en modo producción para generar la carpeta dist:
# npm run build -- --configuration production
# Y asegurarse previamente de la ruta de la carpeta dist generada y nombre de nuestro proyecto en Dockerfile

# 6. Crear imagen frontend  
docker build -t frontend:0.0.1 .

# 7. Crear contenedor frontend 
docker run -p 80:80 --name frontend --network myapp-network -d frontend:0.0.1

# Recomendación: crear un archivo docker-compose.yml y gestionar el arranque de contenedores con Docker Compose

# Ver consumo de contenedores:
docker stats
