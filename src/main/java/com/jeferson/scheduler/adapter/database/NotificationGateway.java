package com.jeferson.scheduler.adapter.database;

import com.jeferson.scheduler.adapter.database.entity.NotificationEntity;
import com.jeferson.scheduler.adapter.database.mapper.NotificationEntityMapper;
import com.jeferson.scheduler.adapter.database.repository.NotificationEntityRepository;
import com.jeferson.scheduler.core.domain.CreateNotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationDomain;
import com.jeferson.scheduler.core.usecase.port.NotificationPersistence;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class NotificationGateway implements NotificationPersistence {

  private final NotificationEntityRepository repository;
  private final NotificationEntityMapper mapper;

  public NotificationGateway(NotificationEntityRepository repository,
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
