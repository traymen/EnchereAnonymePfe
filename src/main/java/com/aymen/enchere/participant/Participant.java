package com.aymen.enchere.participant;

import com.aymen.enchere.common.BaseEntity;
import com.aymen.enchere.enchere.Enchere;
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
public class Participant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idParticipant;
    Float prix ;
    String firstname;
    String lastname;
    String gmail;
    @JsonIgnore
    @ManyToOne
    Enchere enchere;
}
