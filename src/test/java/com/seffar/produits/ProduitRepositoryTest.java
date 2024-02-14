package com.seffar.produits;

import com.seffar.produits.dao.entities.Produit;
import com.seffar.produits.dao.repositories.ProduitRepository;
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

    @Test
    void testCreateProduit(){
        Produit produit = new Produit();
        produit.setNomProduit("Pc HP");
        produit.setPrixProduit(1999.99);
        produit.setDateCreation(LocalDate.now());
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
