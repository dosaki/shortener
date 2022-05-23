FROM nginx:latest

COPY dist/shortener/* /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY 00-take-server-environment-variable.sh /docker-entrypoint.d/