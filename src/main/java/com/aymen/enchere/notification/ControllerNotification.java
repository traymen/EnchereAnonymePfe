
package com.aymen.enchere.notification;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification")

public class ControllerNotification {

    @Autowired
    private ServiceNotification notificationService;

    // Récupérer toutes les notifications
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }
/*
    @GetMapping("/Buuser")
    public List<Notification> getUserNotifications(Authentication authentication) {
        return notificationService.getNotificationsByCreatedBy(authentication);
    }
*/
}
