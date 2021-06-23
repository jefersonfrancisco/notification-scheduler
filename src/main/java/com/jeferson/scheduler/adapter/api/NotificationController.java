package com.jeferson.scheduler.adapter.api;

import com.jeferson.scheduler.adapter.api.dto.CreateNotificationDto;
import com.jeferson.scheduler.adapter.api.mapper.NotificationMapper;
import com.jeferson.scheduler.core.domain.NotificationDomain;
import com.jeferson.scheduler.core.usecase.CreateNotification;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/notifications")
@Produces(MediaType.APPLICATION_JSON)
public class NotificationController {

    private final CreateNotification createNotification;
    private final NotificationMapper mapper;

    @Inject
    public NotificationController(CreateNotification createNotification, NotificationMapper mapper) {
        this.createNotification = createNotification;
        this.mapper = mapper;
    }

    @POST
    public Response create(CreateNotificationDto createNotificationDto) {
        NotificationDomain notificationResponse = createNotification.create(mapper.toDomain(createNotificationDto));
        return Response.status(Response.Status.CREATED)
                .entity(mapper.toResponse(notificationResponse))
                .build();
    }
}
