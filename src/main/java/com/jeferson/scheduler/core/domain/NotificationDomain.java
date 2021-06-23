package com.jeferson.scheduler.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationDomain {

    private String id;
    private String body;
    private NotificationChannelDomain channel;
    private String recipientName;
    private String recipientMail;
    private String recipientPhoneNumber;
    private String recipientPhoneId;
    private LocalDateTime scheduleDate;
}
