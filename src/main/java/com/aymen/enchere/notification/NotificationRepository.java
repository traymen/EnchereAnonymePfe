package com.aymen.enchere.notification;

import com.aymen.enchere.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository  extends JpaRepository<Notification, Integer> {
  //  List<Notification> findBycreatedBy(String createdBy);


}
