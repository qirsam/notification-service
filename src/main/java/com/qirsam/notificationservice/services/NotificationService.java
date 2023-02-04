package com.qirsam.notificationservice.services;

import com.qirsam.notificationservice.dto.NotificationCreateUpdateDto;
import com.qirsam.notificationservice.dto.NotificationReadDto;
import com.qirsam.notificationservice.mapper.NotificationMapper;
import com.qirsam.notificationservice.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Transactional
    public NotificationReadDto create(NotificationCreateUpdateDto notificationCreateUpdateDto) {
        return Optional.of(notificationCreateUpdateDto)
                .map(notificationMapper::toNotification)
                .map(notificationRepository::saveAndFlush)
                .map(notificationMapper::toNotificationReadDto)
                .orElseThrow();
    }


    @Transactional
    public void delete(Long userId, Long taskId) {
        NotificationReadDto notificationReadDto = notificationRepository.findNotificationByUserIdAndTaskId(userId, taskId)
                .orElseThrow(EntityNotFoundException::new);
        notificationRepository.deleteById(notificationReadDto.id());

    }


    public NotificationReadDto findNotificationByUserIdAndTaskId(Long userId, Long taskId) {
        return notificationRepository.findNotificationByUserIdAndTaskId(userId, taskId)
                .orElseThrow(EntityNotFoundException::new);
    }
}
