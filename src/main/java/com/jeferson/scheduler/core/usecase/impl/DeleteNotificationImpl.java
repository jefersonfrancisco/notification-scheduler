package com.jeferson.scheduler.core.usecase.impl;

import com.jeferson.scheduler.core.domain.NotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationStatusDomain;
import com.jeferson.scheduler.core.domain.exception.NotificationAlreadySentException;
import com.jeferson.scheduler.core.usecase.DeleteNotification;
import com.jeferson.scheduler.core.usecase.FindNotification;
import com.jeferson.scheduler.core.usecase.port.NotificationPersistence;
import org.springframework.stereotype.Service;

@Service
public class DeleteNotificationImpl implements DeleteNotification {

  private final NotificationPersistence notificationPersistence;

  private final FindNotification findNotification;

  public DeleteNotificationImpl(NotificationPersistence notificationPersistence,
      FindNotification findNotification) {
    this.notificationPersistence = notificationPersistence;
    this.findNotification = findNotification;
  }

  @Override
  public void delete(String id) {
    NotificationDomain notificationDomain = findNotification.find(id);
    if (NotificationStatusDomain.SENT.equals(notificationDomain.getStatus())) {
      throw new NotificationAlreadySentException(id);
    }
    notificationPersistence.delete(id);
  }
}
