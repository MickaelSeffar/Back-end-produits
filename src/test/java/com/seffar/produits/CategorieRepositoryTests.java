package com.seffar.produits;

import com.seffar.produits.dao.entities.Categorie;
import com.seffar.produits.dao.repositories.CategorieRepository;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CategorieRepositoryTests {

    @Autowired
    CategorieRepository categorieRepository;

    @Test
    void testCreateCategorie(){
        Categorie categorie = new Categorie();
        categorie.setNomCat("PC");
        categorie.setDescriptionCat("Les ordinateurs windows");
        Categorie categorie1 = new Categorie();
        categorie1.setNomCat("Mac");
        categorie1.setDescriptionCat("Les ordinateurs mac");
        categorieRepository.save(categorie);
        categorieRepository.save(categorie1);

    }

    @Test
    void getAll(){
        categorieRepository.findAll()   ;
    }
}


