package com.jeferson.notification.core.usecase.port;

import com.jeferson.notification.core.domain.CreateNotificationDomain;
import com.jeferson.notification.core.domain.NotificationDomain;
import java.util.Optional;

public interface NotificationPersistence {

  NotificationDomain create(CreateNotificationDomain createNotification);

  Optional<NotificationDomain> findById(String id);

  void delete(String id);

}
