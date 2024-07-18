package com.aymen.enchere.favoris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RepositoryFavoris  extends JpaRepository<Favoris, Integer> {
    List<Favoris> findByCreatedBy(String currentUsername);

}
