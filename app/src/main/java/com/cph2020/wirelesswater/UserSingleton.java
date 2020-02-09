package com.cph2020.wirelesswater;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.Date;

class UserSingleton {
    private static UserSingleton ourInstance = null;

    public static UserSingleton getInstance() {
        if (ourInstance == null){
            ourInstance = new UserSingleton();
        }
        return ourInstance;
    }

    private String name;
    private String age;
    private String weight;
    private String height;
    private String sex;
    private String[] allergens;
    private Date date;
    private FirebaseUser firebaseUser;

    private UserSingleton(){

    }

    public static UserSingleton getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(UserSingleton ourInstance) {
        UserSingleton.ourInstance = ourInstance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String[] getAllergens() {
        return allergens;
    }

    public void setAllergens(String[] allergens) {
        this.allergens = allergens;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    public void createUserSingleton(String name, String age, String weight, String height, String sex, String[] allergens) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        this.allergens = allergens;
        this.date = new Date();
    }
}
