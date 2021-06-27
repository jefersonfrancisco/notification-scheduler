package com.jeferson.notification.adapter.api.mapper;

import com.jeferson.notification.adapter.api.dto.CreateNotificationDto;
import com.jeferson.notification.adapter.api.dto.NotificationResponseDto;
import com.jeferson.notification.core.domain.CreateNotificationDomain;
import com.jeferson.notification.core.domain.NotificationChannelDomain;
import com.jeferson.notification.core.domain.NotificationDomain;
import com.jeferson.notification.core.domain.NotificationStatusDomain;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

  public CreateNotificationDomain toDomain(final CreateNotificationDto dto) {
    return CreateNotificationDomain.builder()
        .body(dto.getBody())
        .recipientName(dto.getRecipientName())
        .channel(NotificationChannelDomain.valueOf(dto.getChannel()))
        .scheduleDate(dto.getScheduleDate())
        .recipientMail(dto.getRecipientMail())
        .recipientPhoneNumber(dto.getRecipientPhoneNumber())
        .build();
  }

  public NotificationResponseDto toResponse(final NotificationDomain notificationDomain) {
    return NotificationResponseDto.builder()
        .id(notificationDomain.getId())
        .body(notificationDomain.getBody())
        .recipientName(notificationDomain.getRecipientName())
        .channel(notificationDomain.getChannel())
        .scheduleDate(notificationDomain.getScheduleDate())
        .recipientMail(notificationDomain.getRecipientMail())
        .recipientPhoneNumber(notificationDomain.getRecipientPhoneNumber())
        .status(NotificationStatusDomain.SCHEDULED)
        .build();
  }
}
