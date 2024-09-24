package com.aymen.enchere;

import com.aymen.enchere.enchere.*;
import com.aymen.enchere.file.FileStorageService;
import com.aymen.enchere.notification.ServiceNotification;


import com.aymen.enchere.participant.Participant;
import com.aymen.enchere.participant.ParticipantRepository;
import com.aymen.enchere.participant.ParticipantService;
import com.aymen.enchere.reclamation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@Transactional
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class EnchereApplicationTests {
    @Mock
     private EnchereRepository enchereRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Mock
    private Authentication authentication;
    @InjectMocks
    private ReclamationService reclamationService;

    @Mock
    private ReclamationRepository reclamationRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveParticipation() {
        Integer idEnchere = 1;
        Enchere enchere = new Enchere();
        enchere.setIdEnchere(idEnchere);
        enchere.setNombreCondidat(10); // Assurez-vous que cette valeur n'est pas null

        Participant participant = new Participant();
        participant.setIdParticipant(1);

        when(enchereRepository.findById(idEnchere)).thenReturn(java.util.Optional.of(enchere));

        participantService.saveparticipation(idEnchere, authentication, participant);

        verify(enchereRepository, times(2)).findById(idEnchere); // Vérifiez le nombre d'appels
        verify(participantRepository).save(participant);
        verify(enchereRepository).save(enchere);
    }

    @Test
    void testCreateReclamation() {
        Reclamation reclamation = new Reclamation();
        reclamation.setReponse("Test response");

        when(reclamationRepository.save(reclamation)).thenReturn(reclamation);

        Reclamation createdReclamation = reclamationService.createReclamation(reclamation);

        assertNotNull(createdReclamation);
        assertEquals("Test response", createdReclamation.getReponse());
        verify(reclamationRepository, times(1)).save(reclamation);
    }

    @Test
    void testDeleteReclamation() {
        Integer id = 1;

        reclamationService.deleteReclamation(id);

        verify(reclamationRepository, times(1)).deleteById(id);
    }

    @Test
    void testGetReclamationByType() {
        TypeReclamation type = TypeReclamation.ProblemeEnchere;
        Reclamation reclamation = new Reclamation();
        reclamation.setType(type);

        when(reclamationRepository.findByType(type)).thenReturn(List.of(reclamation));

        List<ReclamationResponse> responses = reclamationService.getReclamationByType(type);

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(type, responses.get(0).getType());
    }

    @Test
    void testGetListReclamation() {
        Reclamation reclamation = new Reclamation();

        when(reclamationRepository.findAll()).thenReturn(List.of(reclamation));

        List<ReclamationResponse> responses = reclamationService.getListReclamtion();

        assertNotNull(responses);
        assertEquals(1, responses.size());
    }

   

    @Test
    void testRepondreReclamation() {
        Integer id = 1;
        Reclamation reclamation = new Reclamation();
        reclamation.setIdReclamation(id);
        reclamation.setReponse("New response");

        when(reclamationRepository.findById(id)).thenReturn(Optional.of(reclamation));
        when(reclamationRepository.save(reclamation)).thenReturn(reclamation);

        reclamationService.repondrereclamation(reclamation, id);

        verify(reclamationRepository, times(1)).save(reclamation);
    }



}
