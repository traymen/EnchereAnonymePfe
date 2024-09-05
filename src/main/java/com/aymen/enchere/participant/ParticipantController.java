package com.aymen.enchere.participant;

import com.aymen.enchere.DTO.HistoriqueParticipation;
import com.aymen.enchere.DTO.ParticipationWithUserDetails;
import com.aymen.enchere.enchere.Enchere;
import com.aymen.enchere.enchere.EnchereService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/participant")
@RestController
@RequiredArgsConstructor
@Tag(name = "Participant")
public class ParticipantController {
    @Autowired
    private  ParticipantService service;

    @PostMapping("/AjouterParticipation")
    public void saveParticipation(
            @RequestParam Integer idEnchere, Authentication connectedUser, @RequestBody Participant participant
    )
    {
        service.saveparticipation(idEnchere, connectedUser,participant);
    }
    @PostMapping("/AjouterParticipationVip")
    public void saveParticipationVip(
            @RequestParam String codeAcces, Authentication connectedUser, @RequestBody Participant participant
    )
    {
        service.saveParticipationVip(codeAcces, connectedUser,participant);
    }

    @GetMapping("/ListeParticipationByIdEnchere")

    public List<Participant> afficherParticipants(@RequestParam Integer idEnch){

        return service.listParticipantByEnchere(idEnch);
    }

    @GetMapping("/historique")
    public List<HistoriqueParticipation> getUserParticipationHistory(Authentication authentication) {
        return service.getUserParticipationHistory(authentication);
    }
/*
    @GetMapping("/enchere/{idEnch}")
    public ResponseEntity<List<ParticipationWithUserDetails>> getParticipationsWithUserDetails(@PathVariable Integer idEnch) {
        List<ParticipationWithUserDetails> participationsWithUserDetails = service.getParticipationsWithUserDetails(idEnch);
        return ResponseEntity.ok(participationsWithUserDetails);
    }
*/
@GetMapping("/counts")
public List<ParticipantCountDTO> getParticipantCounts() {
    return service.getParticipantCounts();
}
}
