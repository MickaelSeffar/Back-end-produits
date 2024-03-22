package com.seffar.produits.restController;

import com.seffar.produits.dao.entities.Categorie;
import com.seffar.produits.dao.entities.Produit;
import com.seffar.produits.model.dtos.ProduitDTO;
import com.seffar.produits.model.mapper.ProduitMapper;
import com.seffar.produits.service.CategorieService;
import com.seffar.produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
    List<ProduitDTO> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        List<ProduitDTO> produitDTOS = new ArrayList<>();
        for (Produit produit : produits) {
            produitDTOS.add(ProduitMapper.convertToDto(produit));
        }
        return produitDTOS;
    }

    @GetMapping("produits/{id}")
    public ResponseEntity<ProduitDTO> getProduit(@PathVariable("id") Long id) {
        Optional<Produit> optionalProduit = produitService.getProduit(id);
        if (optionalProduit.isPresent()) {
            Produit produit = optionalProduit.get();
            ProduitDTO produitDTO = ProduitMapper.convertToDto(produit);
            return ResponseEntity.ok(produitDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("produits")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addProduit(@RequestBody ProduitDTO produitDTO) {
        Produit produit = ProduitMapper.convertToEntity(produitDTO);
        Categorie categorie = produit.getCategorie();

        if (categorie != null && categorie.getId() != null) {
            Optional<Categorie> existingCategorieOpt = categorieService.getCategorie(categorie.getId());
            if (existingCategorieOpt.isPresent()) {
                produit.setCategorie(existingCategorieOpt.get());
            } else {
                System.out.println("La catégorie avec l'ID " + categorie.getId() + " n'existe pas en base de données.");
                produit.setCategorie(categorieService.createCategorie(categorie));
            }
        } else {
            System.out.println("La catégorie du produit n'a pas d'ID ou est nulle.");
            produit.setCategorie(categorieService.createCategorie(categorie));
        }
        produitService.createProduit(produit);
        return ResponseEntity.ok(ProduitMapper.convertToDto(produit));
    }


    @PutMapping("produits/{id}")
    public ResponseEntity updateProduit(@PathVariable Long id, @RequestBody ProduitDTO produitDTO) {
        Optional<Produit> existingProduitOpt = produitService.getProduit(id);
        if (!existingProduitOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Produit existingProduit = existingProduitOpt.get();
        // Mise à jour des attributs du produit avec les valeurs fournies dans le DTO
        existingProduit.setNomProduit(produitDTO.getNomProduit());
        existingProduit.setPrixProduit(produitDTO.getPrixProduit());
        existingProduit.setDateCreation(produitDTO.getDateCreation());

        // Récupération et traitement de la catégorie
        Categorie categorie = produitDTO.getCategorie();
        if (categorie != null) {
            if (categorie.getId() != null) {
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
        }

        // Mettre à jour le produit
        produitService.updateProduit(id, existingProduit);
        return ResponseEntity.ok(existingProduit);
    }


    @DeleteMapping("produits/{id}")
    public ResponseEntity deleteProduit(@PathVariable("id") Long id) {
        Optional<Produit> produitOpt = produitService.getProduit(id);
        if (!produitOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Supprimer le produit
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
