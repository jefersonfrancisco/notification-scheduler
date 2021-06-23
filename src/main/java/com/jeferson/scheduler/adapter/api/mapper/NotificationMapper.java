package com.jeferson.scheduler.adapter.api.mapper;

import com.jeferson.scheduler.adapter.api.dto.CreateNotificationDto;
import com.jeferson.scheduler.adapter.api.dto.NotificationResponseDto;
import com.jeferson.scheduler.core.domain.CreateNotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationDomain;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public CreateNotificationDomain toDomain(final CreateNotificationDto dto) {
        return CreateNotificationDomain.builder()
                .body(dto.getBody())
                .recipientName(dto.getRecipientName())
                .recipientPhoneId(dto.getRecipientPhoneId())
                .channel(dto.getChannel())
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
                .recipientPhoneId(notificationDomain.getRecipientPhoneId())
                .channel(notificationDomain.getChannel())
                .scheduleDate(notificationDomain.getScheduleDate())
                .recipientMail(notificationDomain.getRecipientMail())
                .recipientPhoneNumber(notificationDomain.getRecipientPhoneNumber())
                .status(notificationDomain.getStatus().name())
                .build();
    }
}
