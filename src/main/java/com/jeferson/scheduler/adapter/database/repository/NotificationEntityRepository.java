package com.jeferson.scheduler.adapter.database.repository;

import com.jeferson.scheduler.adapter.database.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public interface NotificationEntityRepository extends JpaRepository <NotificationEntity, String> {
}
