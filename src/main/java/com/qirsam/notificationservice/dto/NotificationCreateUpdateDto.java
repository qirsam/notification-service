package com.qirsam.notificationservice.dto;

import java.time.LocalTime;

public record NotificationCreateUpdateDto(Long userId, Long taskId, LocalTime time) {
}
