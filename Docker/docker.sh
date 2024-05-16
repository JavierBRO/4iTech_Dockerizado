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

# Entrar dentro del contenedor:
docker exec -it mysql-830 bash

# Entrar a mysql por terminal:
mysql -u root -p

# Ver bases de datos:
show databases;

# Salir de mysql y del contenedor...con exit a secas sin ; tambien sale
exit;

