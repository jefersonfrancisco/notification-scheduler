package com.jeferson.scheduler.adapter.api;

import com.jeferson.scheduler.adapter.api.dto.CreateNotificationDto;
import com.jeferson.scheduler.adapter.api.mapper.NotificationMapper;
import com.jeferson.scheduler.core.domain.NotificationDomain;
import com.jeferson.scheduler.core.usecase.CreateNotification;
import com.jeferson.scheduler.core.usecase.DeleteNotification;
import com.jeferson.scheduler.core.usecase.FindNotification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/v1/notifications")
public class NotificationController {

    private final CreateNotification createNotification;
    private final FindNotification findNotification;
    private final DeleteNotification deleteNotification;
    private final NotificationMapper mapper;

    public NotificationController(CreateNotification createNotification,
                                  FindNotification findNotification,
                                  DeleteNotification deleteNotification,
                                  NotificationMapper mapper) {
        this.createNotification = createNotification;
        this.findNotification = findNotification;
        this.deleteNotification = deleteNotification;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateNotificationDto createNotificationDto) {
        NotificationDomain notificationResponse = createNotification.create(mapper.toDomain(createNotificationDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable("id") final String id) {
        NotificationDomain notificationDomain = findNotification.find(id);
        return ResponseEntity.ok(mapper.toResponse(notificationDomain));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final String id) {
        deleteNotification.delete(id);
    }
}
