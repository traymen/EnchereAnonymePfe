package com.aymen.enchere.reclamation;

import com.aymen.enchere.enchere.Enchere;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    public Reclamation createReclamation(Reclamation reclamation) {
        reclamation.setReponse("en cours de traitement");
        return reclamationRepository.save(reclamation);
    }


    public void deleteReclamation(Integer id) {
        reclamationRepository.deleteById(id);
    }

    public List<ReclamationResponse> getReclamationByType(TypeReclamation type) {
        List<Reclamation> reclamations = reclamationRepository.findByType(type);
        return reclamations.stream()
                .map(this::toReclamationResponse)
                .collect(Collectors.toList());
    }
    public List<ReclamationResponse> getListReclamtion() {
        List<Reclamation> reclamations = reclamationRepository.findAll();
        return reclamations.stream()
                .map(this::toReclamationResponse)
                .collect(Collectors.toList());
    }
    public ReclamationResponse toReclamationResponse(Reclamation reclamation) {
        ReclamationResponse response = new ReclamationResponse();
        response.setIdReclamation(reclamation.getIdReclamation());
        response.setReponse(reclamation.getReponse());
        response.setGmail(reclamation.getGmail());
        response.setType(reclamation.getType());
        response.setContenu(reclamation.getContenu());
        response.setFirstname(reclamation.getFirstname());
        response.setLastname(reclamation.getLastname());



        return response;
    }
    public List<ReclamationResponse> getUserReclamationHistory(Authentication connectedUser) {
        String currentUsername = connectedUser.getName();

        List<Reclamation> reclamations = reclamationRepository.findByCreatedBy(currentUsername);
        return reclamations.stream()
                .map(this::mapToReclamationResponse)
                .collect(Collectors.toList());
    }
    private ReclamationResponse mapToReclamationResponse(Reclamation reclamation) {
        ReclamationResponse response = new ReclamationResponse();
        response.setIdReclamation(reclamation.getIdReclamation());
        response.setContenu(reclamation.getContenu());
        response.setType(reclamation.getType());
        response.setFirstname(reclamation.getFirstname());
        response.setLastname(reclamation.getLastname());
        response.setGmail(reclamation.getGmail());
        response.setReponse(reclamation.getReponse());
        return response;
    }

    public void repondrereclamation(Reclamation reclamation , Integer idReclamation) {
        Reclamation reclamation1= reclamationRepository.findById(idReclamation).get();
        reclamation1.setReponse(reclamation.getReponse());

        reclamationRepository.save(reclamation1);

    }
}
