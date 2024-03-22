package com.seffar.produits.model.mapper;

import com.seffar.produits.dao.entities.Categorie;
import com.seffar.produits.model.dtos.CategorieDTO;

public class CategorieMapper {

    public static CategorieDTO convertToDto(Categorie categorie){
        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setId(categorie.getId());
        categorieDTO.setNomCat(categorie.getNomCat());
        categorieDTO.setDescriptionCat(categorie.getDescriptionCat());
        categorieDTO.setProduits(categorie.getProduits());
        return categorieDTO;
    }

    public static Categorie convertToEntity (CategorieDTO categorieDTO){
        Categorie categorie = new Categorie();
        categorie.setId(categorieDTO.getId());
        categorie.setNomCat(categorieDTO.getNomCat());
        categorie.setDescriptionCat(categorieDTO.getDescriptionCat());
        categorie.setProduits(categorieDTO.getProduits());
        return categorie;
    }
}
