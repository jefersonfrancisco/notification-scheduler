package com.jeferson.scheduler.core.domain.exception;

public class NotificationAlreadySentException extends RuntimeException {

  public NotificationAlreadySentException(String id) {
    super(String.format("Notification with ID %s already sent", id));
  }
}
