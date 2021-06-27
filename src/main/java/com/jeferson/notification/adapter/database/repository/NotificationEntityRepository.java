package com.jeferson.notification.adapter.database.repository;

import com.jeferson.notification.adapter.database.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationEntityRepository extends JpaRepository<NotificationEntity, String> {

}
