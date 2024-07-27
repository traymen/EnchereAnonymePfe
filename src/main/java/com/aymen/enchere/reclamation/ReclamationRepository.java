package com.aymen.enchere.reclamation;

import com.aymen.enchere.enchere.TypeEnchere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Integer>  {
    List<Reclamation> findByType(TypeReclamation type);

    List<Reclamation> findByCreatedBy(String currentUsername);
}
