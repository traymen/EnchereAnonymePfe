package com.aymen.enchere.participant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    List<Participant> findByCreatedBy(String createdBy);

  //  List<Participant> findByIdEnchere(Integer idEnch);
}
