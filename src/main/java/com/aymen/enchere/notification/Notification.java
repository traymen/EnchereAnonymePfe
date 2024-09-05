
package com.aymen.enchere.notification;

import com.aymen.enchere.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Notification   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;
     String title;
     String description;
   // String imageUrl;
   Integer auctionId;
}
