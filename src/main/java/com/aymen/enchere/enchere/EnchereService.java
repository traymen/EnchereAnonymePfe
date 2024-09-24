package com.aymen.enchere.enchere;

import com.aymen.enchere.file.FileStorageService;
import com.aymen.enchere.file.FileUtils;
import com.aymen.enchere.notification.ServiceNotification;
import com.aymen.enchere.participant.Participant;
import com.aymen.enchere.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EnchereService  {

    private final EnchereRepository enchereRepository;
    private final FileStorageService fileStorageService;
    private final ServiceNotification notificationService;

    public Integer save(Enchere request, Authentication connectedUser) {
        request.setNombreCondidatsRestants(request.getNombreCondidat());
        request.setNombreCondidatsInscrits(0);
        request.setEtat(Typeetat.Encours);

        // Vérifier si l'enchère est privée
        if (request.isEncherePrivee()) {
            request.setCodeAcces(genererCodeAcces());  // Générer et stocker le code d'accès
        } else {
            request.setCodeAcces(null);  // Si l'enchère n'est pas privée, le code d'accès est nul
        }
        enchereRepository.save(request);
        // Création de la notification
        String description = "Une nouvelle enchère '" + request.getNomProduit() + "'";
        Integer auctionId =  request.getIdEnchere() ;

        //String imageUrl = request.getImage();
        notificationService.createNotification(
                "Nouvelle enchère ajoutée",
                description,
                auctionId
        );

        // Sauvegarde de l'enchère et retour de l'ID
      //  return enchereRepository.save(request).getIdEnchere();
        return auctionId ;
    }

    // Méthode privée pour générer un code d'accès à 4 chiffres
    private String genererCodeAcces() {
        int code = (int) (Math.random() * 9000) + 1000; // Génère un nombre entre 1000 et 9999
        return String.valueOf(code);
    }

    public Optional<Enchere> getEnchereByCodeAcces(String codeAcces) {
        return enchereRepository.findByCodeAcces(codeAcces);
    }
    public List<EnchereResponse> getEncheresByType(TypeEnchere type) {
        List<Enchere> encheres = enchereRepository.findByType(type);
        return encheres.stream()
                .map(this::toEnchereResponse)
                .collect(Collectors.toList());
    }
    public List<EnchereResponse> getListEnchere() {
        List<Enchere> encheres = enchereRepository.findAll();
        return encheres.stream()
                .map(this::toEnchereResponse)
                .collect(Collectors.toList());
    }
    public EnchereResponse toEnchereResponse(Enchere enchere) {
        EnchereResponse response = new EnchereResponse();
        response.setIdEnchere(enchere.getIdEnchere());
        response.setNomProduit(enchere.getNomProduit());
        response.setDescriptionProduit(enchere.getDescriptionProduit());
        response.setNombreCondidat(enchere.getNombreCondidat());
        response.setDate(enchere.getDate());
        response.setHeure(enchere.getHeure());
        response.setImage(FileUtils.readFileFromLocation(enchere.getImage())); // Lire l'image à partir de l'emplacement
        response.setPrix(enchere.getPrix());
        response.setPrixE(enchere.getPrixE());
        response.setPrixGagnant(enchere.getPrixGagnant());
        response.setNombreCondidatsInscrits(enchere.getNombreCondidatsInscrits());
        response.setNombreCondidatsRestants(enchere.getNombreCondidatsRestants());
        response.setType(enchere.getType());
        response.setEtat(enchere.getEtat());
        response.setUsername(enchere.getUsername());

        response.setCodeAcces(enchere.getCodeAcces());



        return response;
    }

    public void uploadEnchereCoverPicture(MultipartFile file, Authentication connectedUser, Integer idEnchere) {
       // if (connectedUser != null) {
            Enchere enchere = enchereRepository.findById(idEnchere)
                    .orElseThrow(() -> new EntityNotFoundException("No enchere found with ID:: " + idEnchere));
          //  var profilePicture = fileStorageService.saveFile(file, connectedUser.getName());
          //  var profilePicture = fileStorageService.saveFile(file, String.valueOf(connectedUser));
              var profilePicture = fileStorageService.saveFile(file);

            enchere.setImage(profilePicture);
            enchereRepository.save(enchere);
     /*   } else {
            // Gérer le cas où connectedUser est null
            throw new IllegalArgumentException("Connected user is null.");
        }
        */
    }

    public void modifEnchere(Enchere enchere, Integer idEnch) {
        Enchere enchere1 = enchereRepository.findById(idEnch).orElseThrow(() -> new RuntimeException("Enchere not found"));

        if (enchere.getNomProduit() != null) {
            enchere1.setNomProduit(enchere.getNomProduit());
        }
        if (enchere.getDescriptionProduit() != null) {
            enchere1.setDescriptionProduit(enchere.getDescriptionProduit());
        }
        if (enchere.getNombreCondidat() != null) {
            enchere1.setNombreCondidat(enchere.getNombreCondidat());
        }
        if (enchere.getDate() != null) {
            enchere1.setDate(enchere.getDate());
        }
        if (enchere.getHeure() != null) {
            enchere1.setHeure(enchere.getHeure());
        }
        if (enchere.getImage() != null && !enchere.getImage().isEmpty()) {
            enchere1.setImage(enchere.getImage());
        }
        if (enchere.getPrix() != null) {
            enchere1.setPrix(enchere.getPrix());
        }

        enchereRepository.save(enchere1);
    }



    public void ajoutEnchereTerminer(Enchere enchere ,Integer idEnch) {
        Enchere enchere1= enchereRepository.findById(idEnch).get();
        enchere1.setPrixGagnant(enchere.getPrixGagnant());
        enchere1.setEtat(Typeetat.Terminer);
        enchereRepository.save(enchere1);

    }
    public void ajoutEnchereTerminerr(Integer idEnchere) {
        // Récupérer l'enchère par son ID
        Enchere enchere = enchereRepository.findById(idEnchere).orElseThrow(() -> new RuntimeException("Enchère non trouvée"));

        // Récupérer la liste des participants de cette enchère
        List<Participant> participants=enchere.getParticipantList();
        if (participants.isEmpty()) {
            throw new RuntimeException("Aucun participant pour cette enchère");
        }

        // Trouver le participant avec le prix le plus élevé
        Participant gagnant = participants.stream()
                .max(Comparator.comparing(Participant::getPrix))
                .orElseThrow(() -> new RuntimeException("Erreur lors du calcul du prix gagnant"));
        String nomGagnant = gagnant.getUserName();
        // Mettre à jour l'enchère avec le prix gagnant et changer l'état
        enchere.setPrixGagnant(gagnant.getPrix());
        enchere.setUsername(nomGagnant);
        enchere.setEtat(Typeetat.Terminer);

        // Sauvegarder les modifications dans la base de données
        enchereRepository.save(enchere);
    }


    public List<EnchereResponse> getListEnchereByid(Integer EcnhereID) {
        Optional<Enchere> encheres = enchereRepository.findById(EcnhereID);
        return encheres.stream()
                .map(this::toEnchereResponse)
                .collect(Collectors.toList());
    }


    public void supprimerEnchere(Integer EnchereId ,Authentication connectedUser){

        enchereRepository.deleteById(EnchereId);
    }

    public void updateParticipantsInfo(Integer enchereId) {
        Enchere enchere = enchereRepository.findById(enchereId).orElse(null);
        if (enchere != null) {
            int nombreParticipants = enchere.getParticipantList() != null ? enchere.getParticipantList().size() : 0;
            enchere.setNombreCondidatsInscrits(nombreParticipants);
            enchere.setNombreCondidatsRestants(enchere.getNombreCondidat() - nombreParticipants);
            enchereRepository.save(enchere);
        }
    }

}
