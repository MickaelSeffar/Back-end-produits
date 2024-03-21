package com.seffar.produits.restController;

import com.seffar.produits.dao.entities.Categorie;
import com.seffar.produits.dao.entities.Produit;
import com.seffar.produits.service.CategorieService;
import com.seffar.produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ProduitController {

    @Autowired
    ProduitService produitService;
    @Autowired
    CategorieService categorieService;

    @GetMapping("produits")
    List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("produits/{id}")
    public ResponseEntity<Produit> getProduit(@PathVariable("id") Long id) {
        Optional<Produit> optionalProduit = produitService.getProduit(id);
        if (optionalProduit.isPresent()) {
            Produit produit = optionalProduit.get();
            return ResponseEntity.ok(produit);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("produits")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addProduit(@RequestBody Produit produit) {
        Categorie categorie = produit.getCategorie();

        // Vérifier si la catégorie a un ID et si elle existe déjà en base de données
        if (categorie != null && categorie.getId() != null) {
            Optional<Categorie> existingCategorieOpt = categorieService.getCategorie(categorie.getId());
            if (existingCategorieOpt.isPresent()) {
                // Si la catégorie existe déjà, utilisez-la
                produit.setCategorie(existingCategorieOpt.get());
            } else {
                System.out.println("La catégorie avec l'ID " + categorie.getId() + " n'existe pas en base de données.");
                // Si la catégorie n'existe pas, enregistrez-la
                produit.setCategorie(categorieService.createCategorie(categorie));
            }
        } else {
            System.out.println("La catégorie du produit n'a pas d'ID.");
            // Si la catégorie n'a pas d'ID, cela signifie qu'elle est nouvelle, alors enregistrez-la
            produit.setCategorie(categorieService.createCategorie(categorie));
        }

        produitService.createProduit(produit);
        return ResponseEntity.ok(produit);
    }

    @PutMapping("produits/{id}")
    public ResponseEntity updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        Optional<Produit> existingProduitOpt = produitService.getProduit(id);
        if (!existingProduitOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Produit existingProduit = existingProduitOpt.get();
        existingProduit.setNomProduit(produit.getNomProduit());
        existingProduit.setPrixProduit(produit.getPrixProduit());
        existingProduit.setDateCreation(produit.getDateCreation());

        Categorie categorie = produit.getCategorie();
        if (categorie != null && categorie.getId() != null) {
            Optional<Categorie> existingCategorieOpt = categorieService.getCategorie(categorie.getId());
            if (existingCategorieOpt.isPresent()) {
                // Si la catégorie existe déjà, utilisez-la
                existingProduit.setCategorie(existingCategorieOpt.get());
            } else {
                System.out.println("La catégorie avec l'ID " + categorie.getId() + " n'existe pas en base de données.");
                // Si la catégorie n'existe pas, enregistrez-la
                existingProduit.setCategorie(categorieService.createCategorie(categorie));
            }
        } else {
            System.out.println("La catégorie du produit n'a pas d'ID.");
            // Si la catégorie n'a pas d'ID, cela signifie qu'elle est nouvelle, alors enregistrez-la
            existingProduit.setCategorie(categorieService.createCategorie(categorie));
        }

        produitService.createProduit(existingProduit);
        return ResponseEntity.ok(existingProduit);
    }

    @DeleteMapping("produits/{id}")
    public ResponseEntity deleteProduit(@PathVariable("id") Long id) {
        Produit produit = produitService.getProduit(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND," Produit inexistant pour cet id"));
        if (produit.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        produitService.deleteProduit(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/prodscat/{id}")
    public ResponseEntity<List<Produit>> getProduitByIdCat(@PathVariable("id") Long id) {
        List<Produit> produits = produitService.findByCategorieId(id);
        if (produits.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(produits);
        }
    }


}
