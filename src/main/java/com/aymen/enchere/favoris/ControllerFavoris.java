package com.aymen.enchere.favoris;

import com.aymen.enchere.DTO.FavorisByUser;
import com.aymen.enchere.DTO.HistoriqueParticipation;
import com.aymen.enchere.participant.Participant;
import com.aymen.enchere.participant.ParticipantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/favoris")
@RestController
@RequiredArgsConstructor
@Tag(name = "Favoris")
@CrossOrigin(origins = "http://localhost:4200") // Configurer CORS pour ce contrôleur spécifique
public class ControllerFavoris {
    @Autowired
    private ServiceFavoris service;

    @PostMapping("/AjoutFavoris")

    public void addFavorite(@RequestParam Integer idEnchere) {
         service.addFavorite(idEnchere);

    }
    @GetMapping("/ListeFavory")
    public List<FavorisByUser> getListFavoriss(Authentication authentication) {
        return service.getFavoris(authentication);
    }

    @DeleteMapping("/favorites/{idFavoris}")
    public void supprimerFavoris(@PathVariable Integer idFavoris, Authentication connectedUser) {

        service.supprimerFavoris(idFavoris , connectedUser);
    }
    }
