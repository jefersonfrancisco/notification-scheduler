package com.jeferson.scheduler.core.domain.exception;

public class NotificationNotFoundException extends RuntimeException {

  public NotificationNotFoundException(String id) {
    super(String.format("Notification scheduling with id %s not found.", id));
  }
}
