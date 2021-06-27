package com.jeferson.scheduler.core.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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
  private LocalDateTime scheduleDate;
}
