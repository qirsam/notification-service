package com.qirsam.notificationservice.repositories;

import com.qirsam.notificationservice.dto.NotificationReadDto;
import com.qirsam.notificationservice.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<NotificationReadDto> findNotificationByUserIdAndTaskId(Long userId, Long taskId);

}