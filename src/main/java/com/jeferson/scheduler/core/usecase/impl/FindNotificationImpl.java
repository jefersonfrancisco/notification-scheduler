package com.jeferson.scheduler.core.usecase.impl;

import com.jeferson.scheduler.core.domain.NotificationDomain;
import com.jeferson.scheduler.core.domain.exception.NotificationNotFoundException;
import com.jeferson.scheduler.core.usecase.FindNotification;
import com.jeferson.scheduler.core.usecase.port.NotificationPersistence;
import org.springframework.stereotype.Service;

@Service
public class FindNotificationImpl implements FindNotification {

    private final NotificationPersistence notificationPersistence;

    public FindNotificationImpl(NotificationPersistence notificationPersistence) {
        this.notificationPersistence = notificationPersistence;
    }

    @Override
    public NotificationDomain find(String id) {
        return notificationPersistence.findById(id).orElseThrow(() -> new NotificationNotFoundException(id));
    }
}