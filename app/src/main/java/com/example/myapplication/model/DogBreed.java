package com.example.myapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class DogBreed implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @ColumnInfo
    @SerializedName("life_span")
    private String lifeSpan;

    @ColumnInfo
    @SerializedName("name")
    private String name;

    @ColumnInfo
    @SerializedName("origin")
    private String origin;

    @ColumnInfo
    @SerializedName("bred_for")
    private String bredFor;

    @ColumnInfo
    @SerializedName("breed_group")
    private String breedGroup;

    @ColumnInfo
    @SerializedName("url")
    private String url;

    public Unit getHeight() {
        return height;
    }

    public void setHeight(Unit height) {
        this.height = height;
    }

    public Unit getWeight() {
        return weight;
    }

    public void setWeight(Unit weight) {
        this.weight = weight;
    }

    @ColumnInfo
    @SerializedName("height")
    private Unit height;

    @ColumnInfo
    @SerializedName("weight")
    private Unit weight;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DogBreed(int id, String lifeSpan, String name, String origin, String bredFor, String breedGroup, String url, Unit weight, Unit height) {
        this.id = id;
        this.lifeSpan = lifeSpan;
        this.name = name;
        this.origin = origin;
        this.bredFor = bredFor;
        this.breedGroup = breedGroup;
        this.url = url;
        this.weight = weight;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getBredFor() {
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }
}
