package com.qirsam.notificationservice.dto;

import java.time.LocalTime;

public record NotificationReadDto(Long id, Long userId, Long taskId, LocalTime time) {
}
