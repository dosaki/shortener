FROM nginx:latest

COPY frontend/shortener/dist/shortener/* /usr/share/nginx/html/
COPY frontend/shortener/nginx.conf /etc/nginx/conf.d/default.conf
COPY frontend/shortener/00-take-server-environment-variable.sh /docker-entrypoint.d/