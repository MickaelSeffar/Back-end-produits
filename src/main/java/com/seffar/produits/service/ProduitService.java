package com.seffar.produits.service;

import com.seffar.produits.dao.entities.Produit;
import com.seffar.produits.dao.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Long id, Produit produit) {
        Optional<Produit> existingProduitOpt = getProduit(id);
        if (!existingProduitOpt.isPresent()) {

            return null;
        }

        Produit existingProduit = existingProduitOpt.get();
        existingProduit.setNomProduit(produit.getNomProduit());
        existingProduit.setPrixProduit(produit.getPrixProduit());
        existingProduit.setDateCreation(produit.getDateCreation());
        existingProduit.setCategorie(produit.getCategorie());
        return produitRepository.save(existingProduit);
    }

    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    public Optional<Produit> getProduit(Long id) {
        return produitRepository.findById(id);
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public List<Produit> findByCategorieId(Long id){
        return produitRepository.findByCategorieId(id);
    }
}
