package com.jeferson.scheduler.adapter.database.mapper;

import com.jeferson.scheduler.adapter.database.entity.NotificationChannelEntity;
import com.jeferson.scheduler.adapter.database.entity.NotificationEntity;
import com.jeferson.scheduler.adapter.database.entity.NotificationStatusEntity;
import com.jeferson.scheduler.core.domain.CreateNotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationChannelDomain;
import com.jeferson.scheduler.core.domain.NotificationDomain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class NotificationEntityMapper {

    public NotificationDomain toDomain(final NotificationEntity entity) {
        return NotificationDomain.builder()
                .id(entity.getId())
                .body(entity.getBody())
                .recipientName(entity.getRecipientName())
                .recipientPhoneId(entity.getRecipientPhoneId())
                .channel(NotificationChannelDomain.valueOf(entity.getChannel().name()))
                .scheduleDate(entity.getScheduleDate())
                .recipientMail(entity.getRecipientMail())
                .recipientPhoneNumber(entity.getRecipientPhoneNumber())
                .build();
    }

    public NotificationEntity toEntity(final CreateNotificationDomain notificationDomain) {
        return NotificationEntity.builder()
                .body(notificationDomain.getBody())
                .recipientName(notificationDomain.getRecipientName())
                .recipientPhoneId(notificationDomain.getRecipientPhoneId())
                .channel(NotificationChannelEntity.valueOf(notificationDomain.getChannel().name()))
                .scheduleDate(notificationDomain.getScheduleDate())
                .recipientMail(notificationDomain.getRecipientMail())
                .recipientPhoneNumber(notificationDomain.getRecipientPhoneNumber())
                .status(NotificationStatusEntity.SCHEDULED)
                .build();
    }
}