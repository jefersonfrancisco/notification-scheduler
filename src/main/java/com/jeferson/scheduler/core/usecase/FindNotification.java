package com.jeferson.scheduler.core.usecase;

import com.jeferson.scheduler.core.domain.NotificationDomain;

public interface FindNotification {

  NotificationDomain find(String id);
}
