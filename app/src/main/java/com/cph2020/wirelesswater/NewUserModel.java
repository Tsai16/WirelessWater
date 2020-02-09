package com.cph2020.wirelesswater;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class NewUserModel {

    static FirebaseDatabase database = FirebaseDatabase.getInstance();

    static DatabaseReference myRef = database.getReference();

    public static void addNewUserToDB(){
        myRef.child("users").child(UserSingleton.getInstance().getFirebaseUser().getUid()).setValue(UserSingleton.getInstance().UserMap());
    }

    public static void getUserFromDB(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                UserSingleton.getInstance().setUserMap(dataSnapshot.child("users").child(UserSingleton.getInstance().getFirebaseUser().getUid()).getValue(Map.class));
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        };
    }
}