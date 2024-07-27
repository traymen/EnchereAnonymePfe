package com.aymen.enchere.enchere;

import com.aymen.enchere.common.BaseEntity;
import com.aymen.enchere.favoris.Favoris;
import com.aymen.enchere.participant.Participant;
import com.aymen.enchere.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Enchere extends BaseEntity {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idEnchere;
    String nomProduit ;
    String descriptionProduit ;
    Integer nombreCondidat ;
    LocalDate date ;
    LocalTime heure ;
    String image ;
    Float Prix ;
    Float PrixGagnant ;
    Float PrixE ;
    Integer nombreCondidatsInscrits;
    Integer nombreCondidatsRestants;
    @Enumerated(EnumType.STRING)
     TypeEnchere type;
    @Enumerated(EnumType.STRING)
    Typeetat etat;

  @JsonIgnore
 @OneToMany(mappedBy = "enchere")
 List<Participant> participantList;

    @OneToMany(mappedBy = "favoris")
    List<Favoris> favorisList;
}
