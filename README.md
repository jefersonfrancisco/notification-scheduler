# Notification Scheduler

Agendamento de notificações

## Arquitetura

A arquitetura do projeto é baseada no padrão hexagonal.


### Schema migration

Para a migração de base é utilizado a biblioteca opensource Flyway disponivel em https://github.com/flyway/flyway

## Build

### Requisitos

- maven
- java 11
- docker (se for executado via container)

Para rodar o build do projeto:

```
mvn clean install
```

Os testes são executados na pipeline do build com os frameworks:

* Junit
* Mockito
* Pitest (testes de mutação)
* Jacoco (cobertura de testes)

## Execução do projeto via docker

```
docker-compose build && docker-compose up
```

Esse comando fará o build da imagem do projeto e levantará o banco de dados e aplicação.

## Execução do projeto via intellij/eclipse

Para rodar o projeto local é necessário ter o banco de dados rodando local. Para subir apenas o banco acessar a pasta docker e executar:

```
docker-compose up
```

Antes de iniciar a aplicação é necessário criar as variaveis de ambiente:

```
POSTGRES_USER=notification;
POSTGRES_PASSWORD=notification;
POSTGRES_URL=jdbc:postgresql://localhost:5433/notification
```

## API

POST

```
http://localhost:8080/v1/notifications

{
    "body": "teste",
    "channel": "EMAIL",
    "recipientName": "dsada",
    "recipientMail": "jefersopn@gmail.com",
    "recipientPhoneNumber": "47999875610",
    "scheduleDate": "2021-06-29T22:53:00.86"
}
```

GET

```
http://localhost:8080/v1/notifications/{id}

```
DELETE

```
http://localhost:8080/v1/notifications/{id}
```

