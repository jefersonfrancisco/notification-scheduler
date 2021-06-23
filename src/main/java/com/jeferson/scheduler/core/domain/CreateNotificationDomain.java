package com.jeferson.scheduler.core.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreateNotificationDomain {
    private String body;
    private NotificationChannelDomain channel;
    private String recipientName;
    private String recipientMail;
    private String recipientPhoneNumber;
    private String recipientPhoneId;
    private LocalDateTime scheduleDate;
}
