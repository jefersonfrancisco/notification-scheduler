package com.jeferson.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO testes unitários com jacoco e mutation, README do projeto, dockerfile, utilizar records do java 14 nos dtos e remover lombok
//TODO Configurar Swagger para facilitar os testes e documentação da API
//TODO Ajustar plugins do POM
@SpringBootApplication(scanBasePackages = "com.jeferson.notification.*")
public class NotificationSchedulerApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationSchedulerApplication.class, args);
  }

}
