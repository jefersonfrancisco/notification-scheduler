package com.jeferson.scheduler.core.usecase;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.jeferson.scheduler.core.domain.NotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationStatusDomain;
import com.jeferson.scheduler.core.domain.exception.NotificationAlreadySentException;
import com.jeferson.scheduler.core.usecase.impl.DeleteNotificationImpl;
import com.jeferson.scheduler.core.usecase.impl.FindNotificationImpl;
import com.jeferson.scheduler.core.usecase.port.NotificationPersistence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteNotificationTest {

  @InjectMocks
  DeleteNotificationImpl deleteNotification;

  @Mock
  NotificationPersistence notificationPersistence;

  @Mock
  FindNotificationImpl findNotification;

  @Test
  public void givenAnIdWhenDeleteThenCallDatabaseLayer() {
    var id = "uuid";

    var notification = NotificationDomain.builder().status(NotificationStatusDomain.SCHEDULED)
        .build();

    when(findNotification.find(id)).thenReturn(notification);

    deleteNotification.delete(id);

    verify(findNotification).find(id);
    verify(notificationPersistence).delete(id);

    verifyNoMoreInteractions(notificationPersistence, findNotification);
  }

  @Test
  public void givenAnIdWhenDeleteThenReturnException() {
    var id = "uuid";

    var notification = NotificationDomain.builder().status(NotificationStatusDomain.SENT).build();

    when(findNotification.find(id)).thenReturn(notification);

    assertThatThrownBy(() -> deleteNotification.delete(id))
        .isInstanceOf(NotificationAlreadySentException.class);

    verify(findNotification).find(id);
    verifyNoMoreInteractions(findNotification);
  }


}
