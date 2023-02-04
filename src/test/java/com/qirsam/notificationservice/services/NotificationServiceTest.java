package com.qirsam.notificationservice.services;

import com.qirsam.notificationservice.dto.NotificationCreateUpdateDto;
import com.qirsam.notificationservice.dto.NotificationReadDto;
import com.qirsam.notificationservice.integration.IntegrationTestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest extends IntegrationTestBase {

    @Autowired
    private NotificationService notificationService;

    @Test
    void findAll() {
        List<NotificationReadDto> result = notificationService.findAll();
        assertThat(result.get(4).time()).isEqualTo(LocalTime.of(22, 34));
    }

    @Test
    void findById() {
        NotificationReadDto result = notificationService.findById(2L);
        assertThat(result.time()).isEqualTo(LocalTime.of(13, 20));
    }

    @Test
    void create() {
        NotificationReadDto result = notificationService.create(
                new NotificationCreateUpdateDto(
                        4L,
                        4L,
                        LocalTime.of(23, 59))
        );

        assertThat(result.time()).isEqualTo(LocalTime.of(23, 59));

    }

    @Test
    void update() {
        NotificationReadDto result = notificationService.update(3L,
                new NotificationCreateUpdateDto(
                        2L,
                        1L,
                        LocalTime.of(10, 45))
        );

        assertThat(result.time()).isEqualTo(LocalTime.of(10, 45));

    }

    @Test
    void delete() {
        notificationService.delete(2L);
        List<NotificationReadDto> result = notificationService.findAll();
        assertThat(result.size()).isEqualTo(6);
    }
}