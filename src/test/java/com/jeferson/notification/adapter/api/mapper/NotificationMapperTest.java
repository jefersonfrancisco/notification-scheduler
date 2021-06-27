package com.jeferson.notification.adapter.api.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.jeferson.notification.adapter.api.dto.CreateNotificationDto;
import com.jeferson.notification.core.domain.NotificationChannelDomain;
import com.jeferson.notification.core.domain.NotificationDomain;
import com.jeferson.notification.core.domain.NotificationStatusDomain;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotificationMapperTest {

  private NotificationMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new NotificationMapper();
  }

  @Test
  public void givenANotificationDomainThenReturnResponseDto() {
    var notificationDomain = NotificationDomain.builder()
        .id("id")
        .body("notification")
        .channel(NotificationChannelDomain.SMS)
        .recipientMail("jeferson.gh@gmail.com")
        .recipientName("Jeferson Francisco")
        .scheduleDate(LocalDateTime.now().plusMinutes(10))
        .recipientPhoneNumber("47999875611")
        .build();

    var response = mapper.toResponse(notificationDomain);

    assertThat(response.getId()).isEqualTo("id");
    assertThat(response.getScheduleDate()).isEqualTo(notificationDomain.getScheduleDate());
    assertThat(response.getBody()).isEqualTo(notificationDomain.getBody());
    assertThat(response.getRecipientName()).isEqualTo(notificationDomain.getRecipientName());
    assertThat(response.getRecipientMail()).isEqualTo(notificationDomain.getRecipientMail());
    assertThat(response.getRecipientPhoneNumber())
        .isEqualTo(notificationDomain.getRecipientPhoneNumber());
    assertThat(response.getChannel()).isEqualTo(NotificationChannelDomain.SMS);
    assertThat(response.getStatus()).isEqualTo(NotificationStatusDomain.SCHEDULED);
  }

  @Test
  public void givenANotificationDtoThenReturnNotificationDomain() {
    var createNotificationDto = CreateNotificationDto.builder()
        .body("notification")
        .channel("SMS")
        .recipientMail("jeferson.gh@gmail.com")
        .recipientName("Jeferson Francisco")
        .scheduleDate(LocalDateTime.now().plusMinutes(10))
        .recipientPhoneNumber("47999875611")
        .build();

    var domain = mapper.toDomain(createNotificationDto);

    assertThat(domain.getScheduleDate()).isEqualTo(createNotificationDto.getScheduleDate());
    assertThat(domain.getBody()).isEqualTo(createNotificationDto.getBody());
    assertThat(domain.getRecipientName()).isEqualTo(createNotificationDto.getRecipientName());
    assertThat(domain.getRecipientMail()).isEqualTo(createNotificationDto.getRecipientMail());
    assertThat(domain.getRecipientPhoneNumber())
        .isEqualTo(createNotificationDto.getRecipientPhoneNumber());
    assertThat(domain.getChannel()).isEqualTo(NotificationChannelDomain.SMS);
  }
}