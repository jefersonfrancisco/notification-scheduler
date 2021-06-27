package com.jeferson.scheduler.adapter.api.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@ToString
@EqualsAndHashCode
public class CreateNotificationDto {

  @NotBlank
  private String body;
  @NotNull
  @Pattern(regexp = "EMAIL|SMS|WHATSAPP")
  private String channel;
  @NotBlank
  private String recipientName;
  @NotBlank
  @Email
  private String recipientMail;
  @Pattern(regexp = "(^$|[0-9]{11})")
  private String recipientPhoneNumber;
  @NotNull
  @Future
  private LocalDateTime scheduleDate;
}
