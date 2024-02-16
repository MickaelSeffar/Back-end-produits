package com.seffar.produits.dao.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Categorie {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomCat;
    private String descriptionCat;

    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits = new ArrayList<>();

    public Categorie() {
    }

    public Categorie(String nomCat, String descriptionCat, List<Produit> produits) {
        this.nomCat = nomCat;
        this.descriptionCat = descriptionCat;
        this.produits = produits;
    }

    public Categorie(String mac, String nosOrdinateursApple) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public String getDescriptionCat() {
        return descriptionCat;
    }

    public void setDescriptionCat(String descriptionCat) {
        this.descriptionCat = descriptionCat;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", nomCat='" + nomCat + '\'' +
                ", descriptionCat='" + descriptionCat + '\'' +
                ", produits=" + produits +
                '}';
    }


}
