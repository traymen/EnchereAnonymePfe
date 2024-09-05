package com.aymen.enchere.enchere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnchereRepository extends JpaRepository<Enchere, Integer> {
    List<Enchere> findByType(TypeEnchere type);
    @Query("SELECT MAX(e.idEnchere) FROM Enchere e")
    Integer findMaxIdEnchere();

    Optional<Enchere> findByCodeAcces(String codeAcces);

}
