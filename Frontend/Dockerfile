# Fase de construcción, compilar el proyecto Angular    hacer pull descarga de imagen, de abajo
# docker pull node:20.11.0-alpine
FROM node:20.11.0-alpine as build

# Establecer directorio de trabajo dentro del contenedor
WORKDIR /app

# Instalar dependencias
COPY package*.json ./
RUN npm i

# Copiar los archivos del proyecto al directorio de trabajo del contenedor
COPY . .

# Construir la aplicación Angular en modo producción para generar la carpeta dist
RUN npm run build -- --configuration production

# docker pull nginx:1.25-alpine
FROM nginx:1.25-alpine

# Copiar configuración nginx
# COPY nginx.conf /etc/nginx/conf.d/default.conf

# Eliminar los archivos por defecto de nginx:
RUN rm -rf /usr/share/nginx/html/*

# Copiar los archivos de la build de Angular a nginx
# /app/dist/nombreproyecto <-- AQUÍ SE PONE EL NOMBRE DEL PROYECTO DE ANGULAR
# COPY --from=build /app/dist/angular-4i-tech/usr/share/nginx/html
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

EXPOSE 80

# Ejecutar nginx en primer plano
CMD ["nginx", "-g", "daemon off;"]

# Construir la imagen frontend
# docker build -t frontend:0.0.1 .
# docker rm frontend
