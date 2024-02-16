package com.seffar.produits;

import com.seffar.produits.dao.entities.Categorie;
import com.seffar.produits.dao.entities.Produit;
import com.seffar.produits.dao.repositories.CategorieRepository;
import com.seffar.produits.dao.repositories.ProduitRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProduitRepositoryTest {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Test
    void testCreateProduit(){

        Optional<Categorie> optionalCategorie = categorieRepository.findById(2L);
        Categorie categorie = optionalCategorie.get();
        Produit produit = new Produit();
        produit.setNomProduit("MacBook Air 15");
        produit.setPrixProduit(1499.99);
        produit.setDateCreation(LocalDate.now());
        produit.setCategorie(categorie);
        produitRepository.save(produit);
    }

    @Test
    void testFindProduits(){
        System.out.println(produitRepository.findAll());
    }

    @Test
    void testFindProduit(){
        System.out.println(produitRepository.findById(1L));
    }

    @Test
    void testUpdateProduit(){
        Optional<Produit> optionalProduit = produitRepository.findById(1L);
        if (optionalProduit.isPresent()) {
            Produit produit = optionalProduit.get();
            produit.setPrixProduit(999.99);
            produitRepository.save(produit);
        }
    }
    @Test
    void deleteProduit(){
        produitRepository.deleteById(1L);
    }

    @Test
    void testFindByNomProduit(){

        List<Produit> produit = produitRepository.findByNomProduit("PC MSI");
        System.out.println(produit);
    }
    @Test
    void testFindByNomProduitContains(){

        List<Produit> produit = produitRepository.findByNomProduitContains("PC");
        System.out.println(produit);
    }

    @Test
    void testFindByNomPrix(){
        List<Produit> produits = produitRepository.findByNomPrix("PC MSI", 20.00);
        System.out.println(produits);
    }
}
