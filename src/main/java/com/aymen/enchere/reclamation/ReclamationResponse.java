package com.aymen.enchere.reclamation;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReclamationResponse {
    private   Integer idReclamation;
    private  String contenu ;
    private  TypeReclamation type;
    private  String firstname;
    private  String lastname;
    private  String gmail;
    private  String reponse;
}
