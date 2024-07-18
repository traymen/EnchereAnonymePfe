package com.aymen.enchere.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavorisByUser {
    String nomProduit ;
    String descriptionProduit ;
    Integer nombreCondidat ;
    LocalDate date ;
    LocalTime heure ;
    byte[]  image ;
    Float prix ;
    Integer idFavoris ;
}
