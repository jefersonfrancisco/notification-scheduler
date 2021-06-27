package com.jeferson.scheduler.adapter.database;


import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.jeferson.scheduler.adapter.database.entity.NotificationEntity;
import com.jeferson.scheduler.adapter.database.mapper.NotificationEntityMapper;
import com.jeferson.scheduler.adapter.database.repository.NotificationEntityRepository;
import com.jeferson.scheduler.core.domain.CreateNotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationDomain;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotificationGatewayTest {


  @InjectMocks
  private NotificationPersistenceGateway gateway;

  @Mock
  private NotificationEntityRepository repository;

  @Mock
  private NotificationEntityMapper mapper;

  @Test
  public void givenACreateNotificationDomainThenCallRepository() {

    var createNotificationDomain = CreateNotificationDomain.builder().build();
    var notificationEntity = NotificationEntity.builder().build();
    var notificationDomain = NotificationDomain.builder().build();

    when(mapper.toEntity(any())).thenReturn(notificationEntity);
    when(repository.save(any())).thenReturn(notificationEntity);
    when(mapper.toDomain(notificationEntity)).thenReturn(notificationDomain);

    Assertions.assertThat(gateway.create(createNotificationDomain)).isEqualTo(notificationDomain);

    verify(mapper).toEntity(createNotificationDomain);
    verify(mapper).toDomain(notificationEntity);
    verify(repository).save(notificationEntity);
    verifyNoMoreInteractions(repository, mapper);
  }

  @Test
  public void givenAnIdWhenFindThenReturnEmpty() {
    var id = "id";
    when(repository.findById(id)).thenReturn(Optional.empty());

    var result = gateway.findById(id);

    Assertions.assertThat(result.isEmpty()).isTrue();
    verify(repository).findById(id);
    verifyNoMoreInteractions();
  }

  @Test
  public void givenAnIdWhenFindThenReturnNotificationDomain() {
    var id = "id";

    var entity = NotificationEntity.builder().build();
    when(repository.findById(id)).thenReturn(Optional.of(entity));
    when(mapper.toDomain(entity)).thenReturn(NotificationDomain.builder().build());

    var result = gateway.findById(id);

    Assertions.assertThat(result.isEmpty()).isFalse();
    verify(repository).findById(id);
    verify(mapper).toDomain(entity);
    verifyNoMoreInteractions(repository, mapper);
  }


  @Test
  public void giveAnIdWhenDeleteCallRepository() {
    var id = "id";
    gateway.delete(id);
    verify(repository).deleteById(id);
    verifyNoMoreInteractions(repository);
  }

}
