package com.aymen.enchere.favoris;

import com.aymen.enchere.DTO.FavorisByUser;
import com.aymen.enchere.DTO.HistoriqueParticipation;
import com.aymen.enchere.enchere.Enchere;
import com.aymen.enchere.enchere.EnchereRepository;
import com.aymen.enchere.file.FileUtils;
import com.aymen.enchere.participant.Participant;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ServiceFavoris {
    @Autowired
    private RepositoryFavoris favoriteRepository;


    @Autowired
    private EnchereRepository enchereRepository;

    public Favoris addFavorite(Integer idEnchere) {

        Enchere enchere = enchereRepository.findById(idEnchere)
                .orElseThrow(() -> new EntityNotFoundException("No enchere found with ID:: " + idEnchere));
        ;

        Favoris favorite = new Favoris();
        favorite.setFavoris(enchere);
        favorite.setFavorisStatus(true); // Mettre le boolean à true

        return favoriteRepository.save(favorite);
    }

    public List<FavorisByUser> getFavoris(Authentication connectedUser) {
        String currentUsername = connectedUser.getName();

        List<Favoris> favorisList = favoriteRepository.findByCreatedBy(currentUsername);

        return favorisList.stream()
                .map(favoris -> {
                    FavorisByUser dto = new FavorisByUser();
                    dto.setPrix(favoris.getFavoris().getPrix());
                    dto.setIdFavoris(favoris.getIdFavoris());

                    dto.setDate(favoris.getFavoris().getDate());
                    dto.setDescriptionProduit(favoris.getFavoris().getDescriptionProduit());
                    dto.setHeure(favoris.getFavoris().getHeure());
                    dto.setNomProduit(favoris.getFavoris().getNomProduit());
                    dto.setImage(FileUtils.readFileFromLocation(favoris.getFavoris().getImage()));


                    return dto;
                })
                .collect(Collectors.toList());
    }

    public void supprimerFavoris(Integer idFavoris ,Authentication connectedUser){

        favoriteRepository.deleteById(idFavoris);
    }
}
