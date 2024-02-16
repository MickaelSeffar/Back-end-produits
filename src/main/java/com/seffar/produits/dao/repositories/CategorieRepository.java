package com.seffar.produits.dao.repositories;

import com.seffar.produits.dao.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
