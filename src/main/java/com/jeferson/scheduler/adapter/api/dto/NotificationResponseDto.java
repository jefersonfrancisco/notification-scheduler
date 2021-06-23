package com.jeferson.scheduler.adapter.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeferson.scheduler.core.domain.NotificationChannelDomain;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationResponseDto {
    private String id;
    private String body;
    private NotificationChannelDomain channel;
    private String recipientName;
    private String recipientMail;
    private String recipientPhoneNumber;
    private String recipientPhoneId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SS")
    private LocalDateTime scheduleDate;
    private String status;
}
