package com.qirsam.notificationservice.mapper;

import com.qirsam.notificationservice.dto.NotificationCreateUpdateDto;
import com.qirsam.notificationservice.dto.NotificationReadDto;
import com.qirsam.notificationservice.models.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);
    @Mapping(target = "id", ignore = true)
    Notification toNotification(NotificationCreateUpdateDto dto);

    NotificationReadDto toNotificationReadDto(Notification notification);
}
