package com.jeferson.scheduler.core.usecase.impl;

import com.jeferson.scheduler.core.domain.CreateNotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationDomain;
import com.jeferson.scheduler.core.usecase.CreateNotification;
import com.jeferson.scheduler.core.usecase.port.NotificationPersistence;
import org.springframework.stereotype.Service;

@Service
public class CreateNotificationImpl implements CreateNotification {

  private final NotificationPersistence notificationPersistence;

  public CreateNotificationImpl(NotificationPersistence notificationPersistence) {
    this.notificationPersistence = notificationPersistence;
  }

  @Override
  public NotificationDomain create(CreateNotificationDomain createNotification) {
    return notificationPersistence.create(createNotification);
  }
}
