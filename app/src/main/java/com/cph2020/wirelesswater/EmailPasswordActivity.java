package com.cph2020.wirelesswater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailpassword);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserSingleton.getInstance().setFirebaseUser(user);
                            Intent setupUserActivityIntent = new Intent(EmailPasswordActivity.this, SetupUserActivity.class);
                            startActivity(setupUserActivityIntent);
                        } else {
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            /*findViewById(R.id.log_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_up_button).setVisibility(View.GONE);
            findViewById(R.id.li_email_et).setVisibility(View.GONE);
            findViewById(R.id.li_password_et).setVisibility(View.GONE);
            findViewById(R.id.su_email_et).setVisibility(View.GONE);
            findViewById(R.id.su_password_et).setVisibility(View.GONE);*/

            Intent homeActivityIntent = new Intent(this, HomeActivity.class);
            startActivity(homeActivityIntent);
        } else {
            findViewById(R.id.log_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_up_button).setVisibility(View.VISIBLE);
            findViewById(R.id.li_email_et).setVisibility(View.GONE);
            findViewById(R.id.li_password_et).setVisibility(View.GONE);
            findViewById(R.id.su_email_et).setVisibility(View.GONE);
            findViewById(R.id.su_password_et).setVisibility(View.GONE);
            final Button signUpButton = findViewById(R.id.sign_up_button);
            final Button logInButton = findViewById(R.id.log_in_button);

            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findViewById(R.id.su_email_et).setVisibility(View.VISIBLE);
                    findViewById(R.id.su_password_et).setVisibility(View.VISIBLE);
                    findViewById(R.id.log_in_button).setVisibility(View.GONE);

                    final EditText emailEt = findViewById(R.id.su_email_et);
                    final EditText passEt = findViewById(R.id.su_password_et);


                    signUpButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String email = emailEt.getText().toString();
                            String pass = passEt.getText().toString();
                            System.out.println(email + "   " + pass);
                            createAccount(email, pass);
                        }
                    });
                }
            });

            logInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findViewById(R.id.li_email_et).setVisibility(View.VISIBLE);
                    findViewById(R.id.li_password_et).setVisibility(View.VISIBLE);
                    findViewById(R.id.sign_up_button).setVisibility(View.GONE);

                    final EditText emailEt = findViewById(R.id.li_email_et);
                    final EditText passEt = findViewById(R.id.li_password_et);


                    logInButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String email = emailEt.getText().toString();
                            String pass = passEt.getText().toString();
                            signIn(email, pass);
                        }
                    });
                }
            });
        }
    }
}
