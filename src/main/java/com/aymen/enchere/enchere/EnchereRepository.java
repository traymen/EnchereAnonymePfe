package com.aymen.enchere.enchere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnchereRepository extends JpaRepository<Enchere, Integer> {
    List<Enchere> findByType(TypeEnchere type);
}
