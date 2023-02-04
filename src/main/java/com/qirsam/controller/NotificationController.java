package com.qirsam.controller;

import com.qirsam.notificationservice.dto.NotificationCreateUpdateDto;
import com.qirsam.notificationservice.dto.NotificationReadDto;
import com.qirsam.notificationservice.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationReadDto>> findAllByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.findAllByUserId(userId));
    }

    @GetMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<NotificationReadDto> findAllByUserIdAndTaskId(@PathVariable Long userId,
                                                                        @PathVariable Long taskId) {
        return ResponseEntity.ok(notificationService.findAllByUserIdAndTaskId(userId, taskId));
    }

    @PostMapping("/")
        public ResponseEntity<?> createNotification(@RequestBody NotificationCreateUpdateDto notification) {
        notificationService.create(notification);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
