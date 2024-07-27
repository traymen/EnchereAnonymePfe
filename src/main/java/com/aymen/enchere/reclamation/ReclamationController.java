package com.aymen.enchere.reclamation;

import com.aymen.enchere.DTO.HistoriqueParticipation;
import com.aymen.enchere.enchere.Enchere;
import com.aymen.enchere.enchere.EnchereResponse;
import com.aymen.enchere.enchere.TypeEnchere;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reclamation")
@RestController
@RequiredArgsConstructor
@Tag(name = "Reclamation")
public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;

    @PostMapping
    public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation) {
        Reclamation newReclamation = reclamationService.createReclamation(reclamation);
        return ResponseEntity.ok(newReclamation);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable Integer id) {
        reclamationService.deleteReclamation(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/AfficherListReclamation")
    @ResponseBody
    public ResponseEntity<List<ReclamationResponse>> getListReclamationn() {
        List<ReclamationResponse> reclamationResponses = reclamationService.getListReclamtion();
        return ResponseEntity.ok(reclamationResponses);
    }
    @GetMapping("/AfficherListReclamtionByType")
    @ResponseBody
    public ResponseEntity<List<ReclamationResponse>> getListReclamationType(TypeReclamation type) {
        List<ReclamationResponse> reclamationResponses = reclamationService.getReclamationByType(type);
        return ResponseEntity.ok(reclamationResponses);
    }
    @GetMapping("/historiqueReclamtion")
    public List<ReclamationResponse> getUserReclamtionHistory(Authentication authentication) {
        return reclamationService.getUserReclamationHistory(authentication);
    }

    @PostMapping("/AjoutReponseReclamtion")
    void ReponseReclamtion(@RequestBody Reclamation reclamation , @RequestParam Integer idReclamation){
        reclamationService.repondrereclamation(reclamation, idReclamation);
    }
}
