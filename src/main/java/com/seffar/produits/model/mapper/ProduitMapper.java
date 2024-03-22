package com.seffar.produits.model.mapper;

import com.seffar.produits.dao.entities.Produit;
import com.seffar.produits.model.dtos.ProduitDTO;

public class ProduitMapper {

    public static ProduitDTO convertToDto(Produit produit) {
        ProduitDTO produitDTO = new ProduitDTO();
        produitDTO.setId(produit.getId());
        produitDTO.setNomProduit(produit.getNomProduit());
        produitDTO.setPrixProduit(produit.getPrixProduit());
        produitDTO.setDateCreation(produit.getDateCreation());
        produitDTO.setCategorie(produit.getCategorie());

        return produitDTO;
    }

    public static Produit convertToEntity(ProduitDTO produitDTO){
        Produit produit = new Produit();
        produit.setId(produitDTO.getId());
        produit.setNomProduit(produitDTO.getNomProduit());
        produit.setPrixProduit(produitDTO.getPrixProduit());
        produit.setDateCreation(produitDTO.getDateCreation());
        produit.setCategorie(produitDTO.getCategorie());

        return produit;
    }
}
