package com.seffar.produits.service;

import com.seffar.produits.dao.entities.Categorie;
import com.seffar.produits.dao.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    CategorieRepository categorieRepository;

    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

//    public Categorie updateCategorie(Categorie Categorie);
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
    public void deleteCategorieById(Long id) {
        categorieRepository.deleteById(id);
    }

    public Optional<Categorie> getCategorie(Long id) {
        return categorieRepository.findById(id);
    }

    public List<Categorie> getAllCategories(){
        return  categorieRepository.findAll();
    }
}
