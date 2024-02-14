package com.seffar.produits;

import com.seffar.produits.dao.entities.Produit;
import com.seffar.produits.service.ProduitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class ProduitServiceTests {

    @Autowired
    ProduitService produitService;


    @Test
    void testCreateProduit(){
        Produit produit = new Produit();
        produit.setNomProduit("PC MSI");
        produit.setPrixProduit(1499.99);
        produit.setDateCreation(LocalDate.now());
        produitService.saveProduit(produit);
    }

    @Test
    void testGetProduits(){
        System.out.println(produitService.getAllProduits());
    }

    @Test
    void testGetProduit(){
        System.out.println(produitService.getProduit(2L));
    }

    @Test
    void testUpdateProduit(){
        Optional<Produit> optionalProduit = produitService.getProduit(1L);
        if (optionalProduit.isPresent()) {
            Produit produit = optionalProduit.get();
            produit.setPrixProduit(3999.99);
            produitService.saveProduit(produit);
        }
    }

    @Test
    void testDeleteProduit(){
        produitService.deleteProduitById(2L);
    }


}
