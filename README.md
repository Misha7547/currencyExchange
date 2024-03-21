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

Для успешного скачивания и подключения к проекту зависимостей из GitHub необходимо настроить Maven конфигурацию в файле settings.xml.

    <parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.17</version>
		<relativePath/> 
	</parent>

Также нужно указать подключение следующих зависимостей apache Maven:
 
    spring-boot-starter-data-jpa
    spring-boot-starter-web
    liquibase-core
    mapstruct
    ostgresql
    lombok
    spring-boot-starter-test

Для работы перобразование данных с сайта cbr нужно подключить XML:

        <dependency>
			<groupId>javax.xml.bind</groupId>
			<artifactId>jaxb-api</artifactId>
			<version>2.3.1</version>
		</dependency>

		<dependency>
			<groupId>org.glassfish.jaxb</groupId>
			<artifactId>jaxb-runtime</artifactId>
			<version>2.3.3</version>
		</dependency>

### Запуск

Стартовая страница находится по адресу : http://localhost:8080/ Сразу при старте система просит подключиться к базе данных postgresSql. Нужно ввести логин/пароль, которые указаны в файле конфигурации src/main/resources/application.yml:

    datasource:
      driver-class-name: org.postgresql.Driver
      url: jdbc:postgresql://localhost:5432/postgres
      username: 
      password: 
