package com.aymen.enchere.enchere;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnchereResponse {
    private Integer idEnchere;
    private String nomProduit;
    private String descriptionProduit;
    private Integer nombreCondidat;
    private LocalDate date;
    private LocalTime heure;
    private byte[] image;
    private Float prix;
    private Float PrixGagnant;
    private Integer nombreCondidatsInscrits;
    private Integer nombreCondidatsRestants;
    private TypeEnchere type;
    private Float PrixE ;
    private Typeetat etat;
  //  private boolean encherePrivee;
    private     String codeAcces;
    private     String username;





}
