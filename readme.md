# Shortener

* Backend: Spring Boot (in [./src](./src))
* Frontend: Angular (in [./frontend](./frontend))
* Database: Postgres (initialisation in [./database](./database))

# How to run

## Live example:
* Shorten your URLs here: https://shortener.dosaki.net/
* Example of a short URL: https://l.dosaki.net/eFZOKOKRh4D

## Using `docker-compose`:
```shell
docker-compose up -d
```

## In development mode:
Make sure you've got a postgres DB set up with [./database/init.sql](./database/init.sql)

Then you can start the backend:
```shell
./mvnw spring-boot:run
```
and the frontend:
```shell
cd frontend/shortener
npm start
```

# Usage

Navigate to `http://localhost:4200` shorten URLs.
Access `http://localhost:4200/admin` for view saved URLs and visit count.

The credentials are hardcoded to:
* Username: `admin`
* Password: `password`