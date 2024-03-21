# Сервис Обмен валюты

## Описание

## Стек используемых технологий
- Java Core
- Spring Boot 2.7
- Maven 3
- Lombok
- Mapstruct
- Liquibase
- PostgreSQL
- XML

## Настройки для запуска

### Зависимости

### Запуск

Стартовая страница находится по адресу : http://localhost:8080/ Сразу при старте система просит подключиться к базе данных postgresSql. Нужно ввести логин/пароль, которые указаны в файле конфигурации src/main/resources/application.yml:

    datasource:
      driver-class-name: org.postgresql.Driver
      url: jdbc:postgresql://localhost:5432/postgres
      username: 
      password: 
