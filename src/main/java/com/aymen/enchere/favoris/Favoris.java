package com.aymen.enchere.favoris;

import com.aymen.enchere.common.BaseEntity;
import com.aymen.enchere.enchere.Enchere;
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
public class Favoris  extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idFavoris;
     boolean favorisStatus;

    @ManyToOne
    Enchere favoris;
}
