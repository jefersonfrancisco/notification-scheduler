package com.jeferson.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO README do projeto, dockerfile
@SpringBootApplication(scanBasePackages = "com.jeferson.notification.*")
public class NotificationSchedulerApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationSchedulerApplication.class, args);
  }

}
