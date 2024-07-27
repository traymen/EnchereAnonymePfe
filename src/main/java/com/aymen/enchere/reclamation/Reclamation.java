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
    String reponse = "en cours de traitement";

}



