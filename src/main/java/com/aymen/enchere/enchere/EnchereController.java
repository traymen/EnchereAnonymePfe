package com.aymen.enchere.enchere;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/enchere")
@RestController
@RequiredArgsConstructor
@Tag(name = "Enchere")
@CrossOrigin(origins = "http://localhost:4200")

public class EnchereController {

    private final EnchereService service;

    @PostMapping ("/AjouterEnchere")
    public ResponseEntity<Integer> saveEnchere(
             @RequestBody Enchere request,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.save(request, connectedUser));
    }
    @GetMapping("/AfficherListEnchere")
    @ResponseBody
    public ResponseEntity<List<EnchereResponse>> getListEnchere() {
        List<EnchereResponse> encheres = service.getListEnchere();
        return ResponseEntity.ok(encheres);
    }
    @GetMapping("/AfficherListEnchereByType")
    @ResponseBody
    public ResponseEntity<List<EnchereResponse>> getListEnchereType(TypeEnchere type) {
        List<EnchereResponse> encheres = service.getEncheresByType(type);
        return ResponseEntity.ok(encheres);
    }

    @PostMapping(value = "/cover/{enchere-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadEnchereCoverPicture(
            @PathVariable("enchere-id") Integer idEnchere,
            @Parameter()
            @RequestPart("file") MultipartFile file,
            Authentication connectedUser
    ) {
        service.uploadEnchereCoverPicture(file, connectedUser, idEnchere);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/ModifierEnchere")
    void modifpost(@RequestBody  Enchere enchere , @RequestParam Integer idEnch){
        service.modifEnchere(enchere, idEnch);
    }

    @PostMapping("/AjoutEnchereTerminer")
    void ajooutEcnhereTerminer(@RequestBody  Enchere enchere , @RequestParam Integer idEnch){
        service.ajoutEnchereTerminer(enchere, idEnch);
    }
    @GetMapping ("/EnchereById")
    void getEnchById( @RequestParam Integer idEnch){
        service.findById(idEnch);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping ("/DeleteEnchree")
    void deleteEnchere( @RequestParam Integer EnchereId,
                        Authentication connectedUser){
        service.supprimerEnchere(EnchereId,
                connectedUser);
    }


}
