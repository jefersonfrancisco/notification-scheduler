package com.jeferson.notification.core.usecase;

import com.jeferson.notification.core.domain.NotificationDomain;

public interface FindNotification {

  NotificationDomain find(String id);
}
