package com.aymen.enchere.notification;

import com.aymen.enchere.enchere.Enchere;
import com.aymen.enchere.enchere.EnchereRepository;
import com.aymen.enchere.participant.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceNotification {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private EnchereRepository enchereRepository;

    public Notification createNotification( String title,String description ,Integer auctionId) {
        Notification notification = new Notification();
        notification.setDescription(description);
        notification.setTitle(title);
        notification.setAuctionId(auctionId);
       /*
        // Récupérer le dernier idEnchere
        Integer lastIdEnchere = enchereRepository.findMaxIdEnchere();
        if (lastIdEnchere != null) {
            // Trouver l'enchère correspondant au dernier ID
            Enchere lastEnchere = enchereRepository.findById(lastIdEnchere).orElse(null);
            if (lastEnchere != null) {
                // Mettre l'image de l'enchère dans la notification
                notification.setImageUrl(lastEnchere.getImage());
            }


        }
*/
        Notification savedNotification = notificationRepository.save(notification);

        // Envoyer la notification via WebSocket
        sendNotification(savedNotification);

        return savedNotification;
    }

    public void sendNotification(Notification notification) {
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
    /*
    public List<Notification> getNotificationsByCreatedBy(Authentication connectedUser) {
        // Extraire le nom d'utilisateur depuis l'objet Authentication
        String currentUsername = connectedUser.getName();

        // Récupérer les notifications créées par cet utilisateur
        return notificationRepository.findBycreatedBy(currentUsername);
    }
     */
}
