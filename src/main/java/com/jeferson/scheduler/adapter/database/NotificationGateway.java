package com.jeferson.scheduler.adapter.database;

import com.jeferson.scheduler.adapter.database.entity.NotificationEntity;
import com.jeferson.scheduler.adapter.database.mapper.NotificationEntityMapper;
import com.jeferson.scheduler.adapter.database.repository.NotificationEntityRepository;
import com.jeferson.scheduler.core.domain.CreateNotificationDomain;
import com.jeferson.scheduler.core.domain.NotificationDomain;
import com.jeferson.scheduler.core.usecase.port.NotificationPersistence;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
@ApplicationScoped
public class NotificationGateway implements NotificationPersistence {

    private final NotificationEntityRepository repository;
    private final NotificationEntityMapper mapper;

    @Inject
    public NotificationGateway(NotificationEntityRepository repository, NotificationEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public NotificationDomain create(CreateNotificationDomain createNotification) {
        NotificationEntity entity = repository.save(mapper.toEntity(createNotification));
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<NotificationDomain> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
