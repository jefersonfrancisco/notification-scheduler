package com.jeferson.scheduler.adapter.database.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.jeferson.scheduler.adapter.database.entity.NotificationChannelEntity;
import com.jeferson.scheduler.adapter.database.entity.NotificationEntity;
import com.jeferson.scheduler.adapter.database.entity.NotificationStatusEntity;
import com.jeferson.scheduler.core.domain.CreateNotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationChannelDomain;
import com.jeferson.scheduler.core.domain.NotificationStatusDomain;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotificationEntityMapperTest {

  private NotificationEntityMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new NotificationEntityMapper();
  }

  @Test
  public void givenACreateNotificationDomainThenReturnEntity() {
    var createNotificationDomain = CreateNotificationDomain.builder()
        .body("notification")
        .channel(NotificationChannelDomain.SMS)
        .recipientMail("jeferson.gh@gmail.com")
        .recipientName("Jeferson Francisco")
        .scheduleDate(LocalDateTime.now().plusMinutes(10))
        .recipientPhoneNumber("47999875611")
        .build();

    var entity = mapper.toEntity(createNotificationDomain);

    assertThat(entity.getId()).isNull();
    assertThat(entity.getScheduleDate()).isEqualTo(createNotificationDomain.getScheduleDate());
    assertThat(entity.getBody()).isEqualTo(createNotificationDomain.getBody());
    assertThat(entity.getRecipientName()).isEqualTo(createNotificationDomain.getRecipientName());
    assertThat(entity.getRecipientMail()).isEqualTo(createNotificationDomain.getRecipientMail());
    assertThat(entity.getChannel()).isEqualTo(NotificationChannelEntity.SMS);
    assertThat(entity.getStatus()).isEqualTo(NotificationStatusEntity.SCHEDULED);
  }

  @Test
  public void givenACreateNotificationEntityThenReturnDomain() {
    var notificationEntity = NotificationEntity.builder()
        .id("id")
        .body("notification")
        .channel(NotificationChannelEntity.SMS)
        .recipientMail("jeferson.gh@gmail.com")
        .recipientName("Jeferson Francisco")
        .scheduleDate(LocalDateTime.now().plusMinutes(10))
        .recipientPhoneNumber("47999875611")
        .status(NotificationStatusEntity.SCHEDULED)
        .build();

    var domain = mapper.toDomain(notificationEntity);

    assertThat(domain.getId()).isEqualTo("id");
    assertThat(domain.getScheduleDate()).isEqualTo(notificationEntity.getScheduleDate());
    assertThat(domain.getBody()).isEqualTo(notificationEntity.getBody());
    assertThat(domain.getRecipientName()).isEqualTo(notificationEntity.getRecipientName());
    assertThat(domain.getRecipientMail()).isEqualTo(notificationEntity.getRecipientMail());
    assertThat(domain.getChannel()).isEqualTo(NotificationChannelDomain.SMS);
    assertThat(domain.getStatus()).isEqualTo(NotificationStatusDomain.SCHEDULED);
  }

}
