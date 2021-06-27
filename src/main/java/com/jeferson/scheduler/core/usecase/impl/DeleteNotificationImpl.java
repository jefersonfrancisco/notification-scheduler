package com.jeferson.scheduler.core.usecase.impl;

import com.jeferson.scheduler.core.domain.exception.NotificationNotFoundException;
import com.jeferson.scheduler.core.usecase.DeleteNotification;
import com.jeferson.scheduler.core.usecase.port.NotificationPersistence;
import org.springframework.stereotype.Service;

@Service
public class DeleteNotificationImpl implements DeleteNotification {

  private final NotificationPersistence notificationPersistence;

  public DeleteNotificationImpl(NotificationPersistence notificationPersistence) {
    this.notificationPersistence = notificationPersistence;
  }

  @Override
  public void delete(String id) {
    if (notificationPersistence.existsById(id)) {
      notificationPersistence.delete(id);
    } else {
      throw new NotificationNotFoundException(id);
    }
  }
}
