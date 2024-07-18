package com.aymen.enchere.reclamation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    public Reclamation createReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    /*
    public Optional<Reclamation> getReclamationById(Integer id) {
        return reclamationRepository.findById(id);
    }
*/
 /*   public Reclamation updateReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }
*/
    public void deleteReclamation(Integer id) {
        reclamationRepository.deleteById(id);
    }
}
