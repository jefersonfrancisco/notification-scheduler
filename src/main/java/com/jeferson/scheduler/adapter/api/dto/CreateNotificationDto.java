package com.jeferson.scheduler.adapter.api.dto;

import com.jeferson.scheduler.core.domain.NotificationChannelDomain;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@EqualsAndHashCode
public class CreateNotificationDto {

    @NotBlank
    private String body;
    @NotNull
    private NotificationChannelDomain channel;
    @NotBlank
    private String recipientName;
    @NotBlank
    private String recipientMail;
    @NotBlank
    private String recipientPhoneNumber;
    @NotBlank
    private String recipientPhoneId;
    @NotNull
    private LocalDateTime scheduleDate;
}
