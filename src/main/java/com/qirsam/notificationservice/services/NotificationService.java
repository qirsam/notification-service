package com.qirsam.notificationservice.services;

import com.qirsam.notificationservice.dto.NotificationCreateUpdateDto;
import com.qirsam.notificationservice.dto.NotificationReadDto;
import com.qirsam.notificationservice.mapper.NotificationMapper;
import com.qirsam.notificationservice.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public List<NotificationReadDto> findAll() {
        return notificationRepository.findAll().stream()
                .map(notificationMapper::toNotificationReadDto)
                .collect(Collectors.toList());
    }

    public NotificationReadDto findById(Long id) {
        return notificationRepository.findById(id)
                .map(notificationMapper::toNotificationReadDto)
                .orElseThrow(() -> new EntityNotFoundException("Notification with id " + id + " not found"));
    }

    public NotificationReadDto create(NotificationCreateUpdateDto notificationCreateUpdateDto) {
        return Optional.of(notificationCreateUpdateDto)
                .map(notificationMapper::toNotification)
                .map(notificationRepository::save)
                .map(notificationMapper::toNotificationReadDto)
                .orElseThrow();
    }

    public NotificationReadDto update(Long id, NotificationCreateUpdateDto dto) {
        return notificationRepository.findById(id)
                .map(notification -> {
                    notification.setUserId(dto.userId());
                    notification.setTaskId(dto.taskId());
                    notification.setTime(dto.time());
                    return notification;
                })
                .map(notificationRepository::save)
                .map(notificationMapper::toNotificationReadDto)
                .orElseThrow(() -> new EntityNotFoundException("Notification with id " + id + " not found"));
    }

    public void delete(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new EntityNotFoundException("Notification with id " + id + " not found");
        }
        notificationRepository.deleteById(id);
    }


}
