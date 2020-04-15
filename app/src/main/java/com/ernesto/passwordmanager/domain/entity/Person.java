package com.ernesto.passwordmanager.domain.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_table")
public class Person {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private int nPassword;

    private long img;

    private String image;

    public Person(String name, int nPassword, long img, String image) {
        this.name = name;
        this.nPassword = nPassword;
        this.img = img;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNPassword() {
        return nPassword;
    }

    public void setNPassword(int nPassword) {
        this.nPassword = nPassword;
    }

    public long getImg() {
        return img;
    }

    public void setImg(long img) {
        this.img = img;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nPassword=" + nPassword +
                ", img=" + img +
                ", image='" + image + '\'' +
                '}';
    }
}
