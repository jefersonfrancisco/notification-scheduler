
CREATE TABLE notification_entity (
   id VARCHAR(64) PRIMARY KEY,
   notification_body VARCHAR(64) NOT NULL,
   notification_channel VARCHAR(64) NOT NULL,
   notification_recipient_name VARCHAR(64) NOT NULL,
   notification_recipient_email VARCHAR(64) NOT NULL,
   notification_recipient_phone_number VARCHAR(64) NOT NULL,
   notification_recipient_phone_id VARCHAR(64) NOT NULL,
   notification_status VARCHAR(64) NOT NULL,
   notification_schedule_date TIMESTAMP NOT NULL
);

CREATE SEQUENCE hibernate_sequence START 1;