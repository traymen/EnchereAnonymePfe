package com.aymen.enchere.reclamation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<Reclamation>> getAllReclamations() {
        List<Reclamation> reclamations = reclamationService.getAllReclamations();
        return ResponseEntity.ok(reclamations);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable Integer id) {
        reclamationService.deleteReclamation(id);
        return ResponseEntity.noContent().build();
    }

}
