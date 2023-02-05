package com.qirsam.notificationservice.services;

import com.qirsam.notificationservice.dto.NotificationCreateUpdateDto;
import com.qirsam.notificationservice.dto.NotificationReadDto;
import com.qirsam.notificationservice.mapper.NotificationMapper;
import com.qirsam.notificationservice.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Collection;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
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

    public List<NotificationReadDto> findAll() {
        return notificationRepository.findAll().stream()
                .map(notificationMapper::toNotificationReadDto)
                .collect(Collectors.toList());
    }

    @Scheduled(fixedDelay = 60000L)
    public void checkTimersAndResponseIfFound() {
        log.info(LocalTime.now() + " check notifications");

        RestTemplate restTemplate = new RestTemplate();

        List<NotificationReadDto> allNotifications = findAll();
        for (NotificationReadDto notification : allNotifications) {
            if (notification.time().getHour() == LocalTime.now().getHour() &&
                notification.time().getMinute() == LocalTime.now().getMinute()){
                restTemplate.postForObject("http://localhost:8080/view/notifications", notification, NotificationReadDto.class);
                log.info(LocalTime.now() + " response for microservice view: " + notification);
            }
        }
    }
}
