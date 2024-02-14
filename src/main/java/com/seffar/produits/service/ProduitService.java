package com.seffar.produits.service;

import com.seffar.produits.dao.entities.Produit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProduitService {

    Produit saveProduit(Produit produit);
    Produit updateProduit(Produit produit);
    void deleteProduit (Produit produit);
    void deleteProduitById(Long id);
    Optional<Produit> getProduit(Long id);
    List<Produit> getAllProduits();
}
