package com.jeferson.notification.core.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.jeferson.notification.core.domain.NotificationDomain;
import com.jeferson.notification.core.domain.exception.NotificationNotFoundException;
import com.jeferson.notification.core.usecase.impl.FindNotificationImpl;
import com.jeferson.notification.core.usecase.port.NotificationPersistence;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FindNotificationTest {

  @InjectMocks
  FindNotificationImpl findNotification;

  @Mock
  NotificationPersistence notificationPersistence;

  @Test
  public void givenAnIdWhenFindThenReturnNotification() {

    var id = "uuid";
    var notificationDomain = NotificationDomain.builder().build();

    when(notificationPersistence.findById(id)).thenReturn(Optional.of(notificationDomain));

    assertThat(findNotification.find(id)).isEqualTo(notificationDomain);

    verify(notificationPersistence).findById(id);
  }

  @Test
  public void givenAnNonexistentIdWhenFindThenReturnException() {

    var id = "uuid";
    when(notificationPersistence.findById(id)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> findNotification.find(id))
        .isInstanceOf(NotificationNotFoundException.class);

    verify(notificationPersistence).findById(id);
    verifyNoMoreInteractions(notificationPersistence);
  }
}
