package com.seffar.produits.dao.repositories;

import com.seffar.produits.dao.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findByNomProduit(String nom);

    List<Produit> findByNomProduitContains(String nom);

//    @Query("select p from Produit p where  p.nomProduit like ?1 and p.prixProduit > ?2")
//    List<Produit> findByNomPrix(String nom, Double prix);

    @Query("select p from Produit  p where p.nomProduit like %:nom and p.prixProduit  >:prix")
    List<Produit> findByNomPrix(@Param("nom") String nom, @Param("prix") Double prix);

    List<Produit> findByCategorieId(Long id);

    List<Produit> findByOrderByNomProduitAsc();
}
