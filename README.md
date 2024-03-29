# Сервис Обмен валюты

Приложение "Обмен Валюты" позволяет добавлять новые валюты в базу данных postgresql, получать ее по Id и конвертировать ее. 
Так же с сайта можно выводить все валюты, хранящиеся в базе даных по их названию и цене. 
В приложении реализован сервис, в котором берется информация  о валютах с сайта cbr XML и добавляетя в базу данных. 
Если валюты нет, то она добавляся в базу данных полностью, если валюта есть в базе, то обновляется  цена валюты. 
Обновление происходит каждый час. 

### Запросы API

Создание новой записи о валюте:

    curl --request POST \
    --url http://localhost:8080/api/currency/create \
    --header 'Content-Type: application/json' \
    --data '{
    "name": "Доллар Готэм-Сити",
    "nominal": 3,
    "value": 32.2,
    "isoNumCode": 1337
    }'

Получение Валюты по id:

    curl --request GET \
      --url http://localhost:8080/api/currency/1333

Конвертация валюты по числовому коду

    curl --request GET \
    --url http://localhost:8080/api/currency/convert?value=100&numCode=840

Получение всеx хранящиеся в базе данных валюты и их стоимость:

    curl --request GET \
      --url http://localhost:8080/api/currency/


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
