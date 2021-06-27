package com.jeferson.notification.core.usecase;

import com.jeferson.notification.core.domain.CreateNotificationDomain;
import com.jeferson.notification.core.domain.NotificationDomain;

public interface CreateNotification {

  NotificationDomain create(CreateNotificationDomain createNotification);

}
