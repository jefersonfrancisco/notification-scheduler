package com.jeferson.scheduler.adapter.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "notification_body")
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_channel")
    private NotificationChannelEntity channel;

    @Column(name = "notification_recipient_name")
    private String recipientName;

    @Column(name = "notification_recipient_email")
    private String recipientMail;

    @Column(name = "notification_recipient_phone_number")
    private String recipientPhoneNumber;

    @Column(name = "notification_recipient_phone_id")
    private String recipientPhoneId;

    @Column(name = "notification_schedule_date")
    private LocalDateTime scheduleDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_status")
    private NotificationStatusEntity status;
}
