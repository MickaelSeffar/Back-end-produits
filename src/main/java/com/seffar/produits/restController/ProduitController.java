package com.seffar.produits.restController;

import com.seffar.produits.dao.entities.Produit;
import com.seffar.produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @GetMapping("produits")
    List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("produits/{id}")
    public ResponseEntity<Produit> getProduit(@PathVariable("id") Long id) {
        Optional<Produit> optionalProduit = produitService.getProduit(id);
        if (optionalProduit.isPresent()){
            Produit produit = optionalProduit.get();
            return ResponseEntity.ok(produit);
        }
        return ResponseEntity.notFound().build();
    }
}
