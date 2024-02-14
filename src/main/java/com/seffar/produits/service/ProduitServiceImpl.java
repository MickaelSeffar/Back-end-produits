package com.seffar.produits.service;

import com.seffar.produits.dao.entities.Produit;
import com.seffar.produits.dao.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Override
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit updateProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public void deleteProduit(Produit produit) {
    produitRepository.delete(produit);
    }

    @Override
    public void deleteProduitById(Long id) {
    produitRepository.deleteById(id);
    }

    @Override
    public Optional<Produit> getProduit(Long id) {
        return produitRepository.findById(id);
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
}
