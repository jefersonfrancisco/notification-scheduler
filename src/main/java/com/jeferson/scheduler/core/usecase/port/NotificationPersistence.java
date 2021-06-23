package com.jeferson.scheduler.core.usecase.port;

import com.jeferson.scheduler.core.domain.CreateNotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationDomain;

import java.util.Optional;

public interface NotificationPersistence {

    NotificationDomain create(CreateNotificationDomain createNotification);

    Optional<NotificationDomain> findById(Long id);

    void delete(Long id);
}