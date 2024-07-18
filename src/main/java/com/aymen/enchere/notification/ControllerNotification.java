/*
package com.aymen.enchere.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/notifications")
public class ControllerNotification {

    @Autowired
    private FCMService fcmService;

    @PostMapping("/send")
    public String sendNotification(@RequestParam String token, @RequestParam String title, @RequestParam String body) {
        try {
            fcmService.sendNotification(token, title, body);
            return "Notification sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send notification";
        }
    }
}*/
