package com.jeferson.scheduler.core.usecase.impl;

import com.jeferson.scheduler.core.domain.CreateNotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationDomain;
import com.jeferson.scheduler.core.usecase.CreateNotification;
import com.jeferson.scheduler.core.usecase.port.NotificationPersistence;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class CreateNotificationImpl implements CreateNotification {

    private final NotificationPersistence notificationPersistence;

    @Inject
    public CreateNotificationImpl(NotificationPersistence notificationPersistence) {
        this.notificationPersistence = notificationPersistence;
    }

    @Override
    public NotificationDomain create(CreateNotificationDomain createNotification) {
        return notificationPersistence.create(createNotification);
    }
}
