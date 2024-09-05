package com.aymen.enchere.reclamation;

import com.aymen.enchere.common.BaseEntity;
import com.aymen.enchere.enchere.Enchere;
import com.aymen.enchere.enchere.TypeEnchere;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
public class Reclamation   extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idReclamation;
    String contenu ;
    @Enumerated(EnumType.STRING)
    TypeReclamation type;
    String firstname;
    String lastname;
    String gmail;
   // String reponse = "en cours de traitement";
   String reponse ;
    @Enumerated(EnumType.STRING)  // Cette annotation permet de stocker la valeur textuelle
    EtatReclamation etat;  // Nouveau champ pour l'état de traitement
    public String getEtatDescription() {
        if (etat == EtatReclamation.EN_COURS) {
            return "En cours de traitement";
        } else if (etat == EtatReclamation.TRAITE) {
            return "Traité";
        }
        return "";
    }
}



