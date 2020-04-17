package com.ernesto.passwordmanager.domain.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "password_table")
public class Password {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int person_id;

    private String application;

    private String user;

    private String password;

    private int appImg;

    private String imgName;

    public Password(int person_id, String application, String user, String password, int appImg, String imgName) {
        this.person_id = person_id;
        this.application = application;
        this.user = user;
        this.password = password;
        this.appImg = appImg;
        this.imgName = imgName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAppImg() {
        return appImg;
    }

    public void setAppImg(int appImg) {
        this.appImg = appImg;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", person_id=" + person_id +
                ", application='" + application + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", appImg=" + appImg +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
