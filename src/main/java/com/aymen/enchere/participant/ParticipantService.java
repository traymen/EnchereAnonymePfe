package com.aymen.enchere.participant;

import com.aymen.enchere.DTO.HistoriqueParticipation;
import com.aymen.enchere.DTO.ParticipationWithUserDetails;
import com.aymen.enchere.ServiceKeyclock.KeycloakServicee;
import com.aymen.enchere.enchere.Enchere;
import com.aymen.enchere.enchere.EnchereRepository;
import com.aymen.enchere.file.FileUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipantService {
@Autowired
    EnchereRepository enchereRepository ;
@Autowired
    ParticipantRepository participantRepository;
  //  @Autowired

 //   private KeycloakServicee keycloakService;

/*
    @Autowired
    private KeycloakSecurityContext keycloakSecurityContext;

    // Autres injections et méthodes

    public String getNickName() {
        return keycloakSecurityContext.getToken().getNickName();
    }
    */
    public void saveparticipation(Integer idEnchere, Authentication connectedUser, Participant participant) {

        Enchere enchere = enchereRepository.findById(idEnchere)
                .orElseThrow(() -> new EntityNotFoundException("No enchere found with ID:: " + idEnchere));

        participant.setEnchere(enchere);
        participantRepository.save(participant);
        updateParticipantsInfo(idEnchere);

    }
    public void saveParticipationVip(String codeAcces, Authentication connectedUser , Participant participant ) {
        Optional<Enchere> enchereOptional = enchereRepository.findByCodeAcces(codeAcces);

        if (enchereOptional.isPresent()) {
            Enchere enchere = enchereOptional.get();
            participant.setEnchere(enchere);
            participantRepository.save(participant);
            updateParticipantsInfo(enchere.getIdEnchere()); // Assurez-vous que l'ID est correct
        } else {
            // Gérer le cas où l'enchère n'est pas trouvée
            throw new IllegalArgumentException("Enchère introuvable avec le code d'accès : " + codeAcces);
        }
    }


    private void updateParticipantsInfo(Integer enchereId) {
        Enchere enchere = enchereRepository.findById(enchereId)
                .orElseThrow(() -> new EntityNotFoundException("Aucune enchère trouvée avec l'ID : " + enchereId));

        int nombreParticipants = enchere.getParticipantList() != null ? enchere.getParticipantList().size() : 0;
        enchere.setNombreCondidatsInscrits(nombreParticipants);
        enchere.setNombreCondidatsRestants(enchere.getNombreCondidat() - nombreParticipants);

        enchereRepository.save(enchere);
    }


    public List<Participant> listParticipantByEnchere(Integer idEnch) {
        Enchere enchere=enchereRepository.findById(idEnch).get();
        List<Participant> participantList=enchere.getParticipantList();

        return participantList;
    }

/*
public List<Participant> listParticipantByEnchere(Integer idEnch) {
    Enchere enchere = enchereRepository.findById(idEnch).orElseThrow(() -> new RuntimeException("Enchere not found"));
    List<Participant> participantList = enchere.getParticipantList();

    for (Participant participant : participantList) {
        String nickName = getNickName();
        participant.setNickName(nickName);
    }

    return participantList;
}
*/
    public List<HistoriqueParticipation> getUserParticipationHistory(Authentication connectedUser) {
        String currentUsername = connectedUser.getName();

        List<Participant> participants = participantRepository.findByCreatedBy(currentUsername);

        return participants.stream()
                .map(participant -> {
                    HistoriqueParticipation dto = new HistoriqueParticipation();
                    dto.setPrixBoutique(participant.getEnchere().getPrix());
                    dto.setDate(participant.getEnchere().getDate());
                    dto.setDescriptionProduit(participant.getEnchere().getDescriptionProduit());
                    dto.setHeure(participant.getEnchere().getHeure());
                    dto.setNomProduit(participant.getEnchere().getNomProduit());
                    dto.setImage(FileUtils.readFileFromLocation(participant.getEnchere().getImage()));
                    dto.setPrix(participant.getPrix());
                    dto.setCodeAcces(participant.getEnchere().getCodeAcces());


                    return dto;
                })
                .collect(Collectors.toList());
    }

/*
    public List<Participant> listParticipantByEnchere(Integer idEnch) {
        Enchere enchere = enchereRepository.findById(idEnch)
                .orElseThrow(() -> new EntityNotFoundException("Enchère non trouvée avec l'ID : " + idEnch));

        List<Participant> participantList = enchere.getParticipantList();

        // Récupérer les détails des utilisateurs à partir de Keycloak pour chaque participant
        for (Participant participant : participantList) {
            String userId = participant.getCreatedBy(); // Récupérer l'ID de l'utilisateur
            UserRepresentation user = keycloakRestTemplate.getForObject(
                    "/admin/realms/{realm}/users/{id}",
                    UserRepresentation.class,
                    "enchere", userId); // Remplacez "votre-realm" par le nom de votre realm Keycloak

            if (user != null) {
                participant.setUserName(user.getUsername());
                participant.setEmail(user.getEmail());
                // Ajoutez d'autres informations d'utilisateur si nécessaire
            }
        }

        return participantList;
    }
    */

/*
    public List<ParticipationWithUserDetails> getParticipationsWithUserDetails(Integer idEnch) {
        Optional<Enchere> optionalEnchere = enchereRepository.findById(idEnch);
        if (optionalEnchere.isEmpty()) {
            return Collections.emptyList(); // ou lever une exception appropriée
        }

        Enchere enchere = optionalEnchere.get();
        List<Participant> participantList = enchere.getParticipantList();

        List<String> userIds = participantList.stream()
                .map(Participant::getCreatedBy)
                .collect(Collectors.toList());

        List<org.keycloak.representations.idm.UserRepresentation> userDetails = keycloakService.getUsersDetails(userIds);

        return participantList.stream()
                .map(participation -> {
                    UserRepresentation userDetail = userDetails.stream()
                            .filter(user -> user.getId().equals(participation.getIdParticipant()))
                            .findFirst()
                            .orElse(null); // Gérer le cas où userDetail est null
                    return new ParticipationWithUserDetails(participation, userDetail);
                })
                .collect(Collectors.toList());
    }

 */

    public List<ParticipantCountDTO> getParticipantCounts() {
        return participantRepository.countParticipationByUser();
    }
}


