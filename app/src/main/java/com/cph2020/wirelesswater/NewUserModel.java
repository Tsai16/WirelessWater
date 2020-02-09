package com.cph2020.wirelesswater;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class NewUserModel {

    private static FirebaseUser firebaseUser;

    public static void addNewUserToDB(DatabaseReference reference, String name, String age, String weight,
                                      String height, String sex, String[] allergens, String email){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        Map<String, Object> features = new HashMap<>();
        features.put("name", name);
        features.put("age", age);
        features.put("weight", weight);
        features.put("height", height);
        features.put("sex", sex);
        features.put("allergens", Arrays.asList(allergens));
        features.put("start_date", dateFormat.format(date));

        reference.child("users").child(UserSingleton.getInstance().getFirebaseUser().getUid()).setValue(features);
    }
}