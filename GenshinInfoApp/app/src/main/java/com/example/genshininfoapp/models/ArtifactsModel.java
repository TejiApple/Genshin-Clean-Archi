package com.example.genshininfoapp.models;

public class ArtifactsModel {
    String id;
    String name;
    String slug;
    String bonus_2_set;
    String bonus_4_set;
    String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBonus_2_set() {
        return bonus_2_set;
    }

    public void setBonus_2_set(String bonus_2_set) {
        this.bonus_2_set = bonus_2_set;
    }

    public String getBonus_4_set() {
        return bonus_4_set;
    }

    public void setBonus_4_set(String bonus_4_set) {
        this.bonus_4_set = bonus_4_set;
    }
}
