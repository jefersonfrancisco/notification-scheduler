package com.jeferson.scheduler.core.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.jeferson.scheduler.core.domain.CreateNotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationChannelDomain;
import com.jeferson.scheduler.core.domain.NotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationStatusDomain;
import com.jeferson.scheduler.core.usecase.impl.CreateNotificationImpl;
import com.jeferson.scheduler.core.usecase.port.NotificationPersistence;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateNotificationTest {

  @InjectMocks
  CreateNotificationImpl createNotificationUseCase;

  @Mock
  NotificationPersistence notificationPersistence;

  @Test
  public void givenANotificationWhenCreateThenReturnScheduledNotification() {

    var createNotificationDomain = CreateNotificationDomain.builder()
        .body("notification")
        .channel(NotificationChannelDomain.SMS)
        .recipientMail("jeferson.gh@gmail.com")
        .recipientName("Jeferson Francisco")
        .scheduleDate(LocalDateTime.now().plusMinutes(10))
        .recipientPhoneNumber("47999875611")
        .build();

    when(notificationPersistence.create(any()))
        .thenAnswer(answer -> buildDomainResponse(createNotificationDomain));

    var response = createNotificationUseCase.create(createNotificationDomain);

    assertThat(response.getId()).isNotNull();
    assertThat(response.getScheduleDate()).isEqualTo(createNotificationDomain.getScheduleDate());
    assertThat(response.getBody()).isEqualTo(createNotificationDomain.getBody());
    assertThat(response.getRecipientName()).isEqualTo(createNotificationDomain.getRecipientName());
    assertThat(response.getRecipientMail()).isEqualTo(createNotificationDomain.getRecipientMail());
    assertThat(response.getChannel()).isEqualTo(createNotificationDomain.getChannel());
    assertThat(response.getStatus()).isEqualTo(NotificationStatusDomain.SCHEDULED);

    verify(notificationPersistence).create(createNotificationDomain);
    verifyNoMoreInteractions(notificationPersistence);
  }

  public NotificationDomain buildDomainResponse(CreateNotificationDomain response) {
    return NotificationDomain.builder().id(UUID.randomUUID().toString())
        .body(response.getBody())
        .channel(response.getChannel())
        .recipientMail(response.getRecipientMail())
        .recipientName(response.getRecipientName())
        .scheduleDate(response.getScheduleDate())
        .status(NotificationStatusDomain.SCHEDULED)
        .recipientPhoneNumber(response.getRecipientPhoneNumber())
        .build();
  }
}
