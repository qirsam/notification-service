package com.qirsam.notificationservice.mapper;

import com.qirsam.notificationservice.dto.NotificationCreateUpdateDto;
import com.qirsam.notificationservice.dto.NotificationReadDto;
import com.qirsam.notificationservice.models.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    @Mapping(target = "id", ignore = true)
    Notification toNotification(NotificationCreateUpdateDto dto);

    NotificationReadDto toNotificationReadDto(Notification notification);

//    List<NotificationReadDto> toListNotificationsReadDto(List<NotificationReadDto> dtos);
    // FIXME: 03.02.2023
}