package com.jeferson.notification.adapter.database;

import com.jeferson.notification.adapter.database.entity.NotificationEntity;
import com.jeferson.notification.adapter.database.mapper.NotificationEntityMapper;
import com.jeferson.notification.adapter.database.repository.NotificationEntityRepository;
import com.jeferson.notification.core.domain.CreateNotificationDomain;
import com.jeferson.notification.core.domain.NotificationDomain;
import com.jeferson.notification.core.usecase.port.NotificationPersistence;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class NotificationPersistenceGateway implements NotificationPersistence {

  private final NotificationEntityRepository repository;

  private final NotificationEntityMapper mapper;

  public NotificationPersistenceGateway(NotificationEntityRepository repository,
      NotificationEntityMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public NotificationDomain create(CreateNotificationDomain createNotification) {
    NotificationEntity entity = repository.save(mapper.toEntity(createNotification));
    return mapper.toDomain(entity);
  }

  @Override
  public Optional<NotificationDomain> findById(String id) {
    Optional<NotificationEntity> entity = repository.findById(id);
    return entity.map(mapper::toDomain);
  }

  @Override
  public void delete(String id) {
    repository.deleteById(id);
  }

}
