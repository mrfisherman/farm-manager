package com.mrfisherman.farmmanager;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Animal {
    private @Id String id;
    private String name;
    private int age;
    private DairyAnimalSpecies species;
    private Sex sex;
    private Pregnancy pregnancy;

    public Animal() {
    }

    public Animal(String id, String name, int age, DairyAnimalSpecies species, Sex sex, Pregnancy pregnancy) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.species = species;
        this.sex = sex;
        this.pregnancy = pregnancy;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public DairyAnimalSpecies getSpecies() {
        return species;
    }

    public void setSpecies(DairyAnimalSpecies species) {
        this.species = species;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Pregnancy getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(Pregnancy pregnancy) {
        this.pregnancy = pregnancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return this.age == animal.age
                && Objects.equals(this.id, animal.id)
                && Objects.equals(this.name, animal.name)
                && this.species == animal.species
                && this.sex == animal.sex
                && this.pregnancy == animal.pregnancy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.age, this.species, this.sex, this.pregnancy);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", age=" + this.age +
                ", species=" + this.species +
                ", sex=" + this.sex +
                ", pregnancy=" + this.pregnancy +
                '}';
    }
}
