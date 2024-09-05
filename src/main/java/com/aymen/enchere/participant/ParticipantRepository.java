package com.aymen.enchere.participant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    List<Participant> findByCreatedBy(String createdBy);

  //  List<Participant> findByIdEnchere(Integer idEnch);
  @Query("SELECT new com.aymen.enchere.participant.ParticipantCountDTO(p.firstname, p.lastname, p.gmail, COUNT(p)) " +
          "FROM Participant p GROUP BY p.firstname, p.lastname, p.gmail")
  List<ParticipantCountDTO> countParticipationByUser();
}
