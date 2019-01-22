package com.example.pavanbora.movieshows;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    private static final String PREF_NAME = "pre";
    private static final String TAG = PackageManager.class.getName();
    EditText username, password;
    TextView textView;
    Button b1, b2, b3;
    CardView card;
    boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        card = findViewById(R.id.card);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        textView = findViewById(R.id.textView);
        b1 = findViewById(R.id.login);
        b2 = findViewById(R.id.button);

        b3 = findViewById(R.id.button3);
        mAuth = FirebaseAuth.getInstance();
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        if (preferences.getBoolean("isLoggedIn", isLoggedIn))
        {
            Intent intent = new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
            finish();
        }
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog;
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading ....");
                progressDialog.setTitle("Authenticating");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                progressDialog.setCancelable(false);

                String user = username.getText().toString();
                String pass = password.getText().toString();
                mAuth.signInWithEmailAndPassword(user, pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    isLoggedIn=true;
                                    SharedPreferences preferences1=getSharedPreferences( PREF_NAME,MODE_PRIVATE );
                                    SharedPreferences.Editor editor=preferences1.edit();
                                    editor.putBoolean( "isLoggedIn",isLoggedIn );
                                    editor.apply();
                                    editor.commit();
                                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                                    startActivity(intent);
                                    Log.d(TAG, "signInWithEmail:success");
                                    Toast.makeText(MainActivity.this, "you are loggedin", Toast.LENGTH_LONG).show();
                                    finish();
                                    progressDialog.dismiss();
                                }
                                else
                                {

                                    Log.d(TAG,"LOGIN ERROR", task.getException());
                                    Toast.makeText(MainActivity.this,"Authentication failed.Please try again later",Toast.LENGTH_LONG).show();
                                }
                            }
                        });


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Forgot.class);
                startActivity(intent);
            }
        });
    }
}

