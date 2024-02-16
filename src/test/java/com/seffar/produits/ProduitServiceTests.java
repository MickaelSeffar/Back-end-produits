package com.seffar.produits;

import com.seffar.produits.dao.entities.Categorie;
import com.seffar.produits.dao.entities.Produit;
import com.seffar.produits.service.CategorieService;
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

    @Autowired
    CategorieService categorieService;

    @Test
    void testCreateProduit() {
        Produit produit = new Produit();
        Optional<Categorie> optionalCategorie = categorieService.getCategorie(2L);
        Categorie categorie = optionalCategorie.get();
        produit.setNomProduit("MacBook Air 16");
        produit.setPrixProduit(2999.99);
        produit.setCategorie(categorie);
        produit.setDateCreation(LocalDate.now());

        // Appel de la méthode createProduit du service
        produitService.createProduit(produit);

        // Ajoutez vos assertions ou vérifications ici si nécessaire
    }

    @Test
    void testGetProduits() {
        System.out.println(produitService.getAllProduits());
    }

    @Test
    void testGetProduit() {
        System.out.println(produitService.getProduit(2L));
    }

    @Test
    void testUpdateProduit() {
        Optional<Produit> optionalProduit = produitService.getProduit(1L);
        if (optionalProduit.isPresent()) {
            Produit produit = optionalProduit.get();
            produit.setPrixProduit(3999.99);
            produitService.createProduit(produit);
        }
    }

    @Test
    void testDeleteProduit() {
        produitService.deleteProduitById(2L);
    }


}
