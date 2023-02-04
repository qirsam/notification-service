package com.qirsam.notificationservice.repositories;

import com.qirsam.notificationservice.dto.NotificationReadDto;
import com.qirsam.notificationservice.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query(value = "SELECT n FROM Notification n WHERE n.userId = ?")
    List<Notification> findAllByUserId(Long userId);

    @Query(value = "SELECT n FROM Notification n WHERE n.userId = ? AND n.taskId = ?")
    Optional<NotificationReadDto> findAllByUserIdAndTaskId(Long userId, Long taskId);
}