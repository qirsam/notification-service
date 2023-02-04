package com.qirsam.notificationservice.controller;

import com.qirsam.notificationservice.dto.NotificationCreateUpdateDto;
import com.qirsam.notificationservice.dto.NotificationReadDto;
import com.qirsam.notificationservice.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{userId}")
    public ResponseEntity<NotificationReadDto> findNotificationByUserIdAndTaskId(@PathVariable Long userId,
                                                                                 @RequestParam Long taskId) {
        return ResponseEntity.ok(notificationService.findNotificationByUserIdAndTaskId(userId, taskId));
    }


    @PostMapping
    public ResponseEntity<?> createNotification(@RequestBody NotificationCreateUpdateDto notification) {
        notificationService.create(notification);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long userId,
                                                @RequestParam Long taskId) {
        notificationService.delete(userId, taskId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
