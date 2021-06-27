package com.jeferson.scheduler.core.usecase;

import com.jeferson.scheduler.core.domain.CreateNotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationDomain;

public interface CreateNotification {

  NotificationDomain create(CreateNotificationDomain createNotification);

}
