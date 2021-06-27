package com.jeferson.scheduler.core.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationDomain {

  private String id;
  private String body;
  private NotificationChannelDomain channel;
  private String recipientName;
  private String recipientMail;
  private String recipientPhoneNumber;
  private LocalDateTime scheduleDate;
  private NotificationStatusDomain status;
}
