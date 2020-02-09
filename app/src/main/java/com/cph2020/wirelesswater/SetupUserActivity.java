package com.cph2020.wirelesswater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetupUserActivity extends AppCompatActivity {

    private NewUserModel newUserModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_user);

        final EditText name = findViewById(R.id.name_et);
        final EditText age = findViewById(R.id.age_et);
        final EditText weight = findViewById(R.id.weight_et);
        final EditText height = findViewById(R.id.height_et);
        final EditText sex = findViewById(R.id.sex_et);
        final EditText allergens = findViewById(R.id.allergens_et);
        Button save = findViewById(R.id.save_setup_button);




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String a = age.getText().toString();
                String w = weight.getText().toString();
                String h = height.getText().toString();
                String s = sex.getText().toString();
                String al = allergens.getText().toString();
                al = al.replaceAll(" ", "");
                String[] alls = al.split(",");
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();


                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                NewUserModel.addNewUserToDB(myRef, n, a, w, h, s, alls, email);
                UserSingleton.getInstance().createUserSingleton(n,a,w,h,s,alls);

                Intent homeActivityIntent = new Intent(SetupUserActivity.this, HomeActivity.class);
                startActivity(homeActivityIntent);
            }
        });

    }
}
