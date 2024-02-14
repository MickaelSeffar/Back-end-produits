package com.seffar.produits.dao.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categorie {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomCat;
    private String descriptionCat;

    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;


}
