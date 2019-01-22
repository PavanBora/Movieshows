package com.example.pavanbora.movieshows;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class

register extends AppCompatActivity {
    private static final String TAG = PackageManager.class.getName();
    private static final String PREF_NAME="pref";
    private FirebaseAuth mAuth;
    EditText username,password;
    TextView textView;
    Button b4;
    CardView register;
    boolean isLoggedIn=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        username=findViewById(R.id.regusername);
        password=findViewById(R.id.regpassword);
        register=findViewById(R.id.registercard);
        b4=findViewById(R.id.registerbutton);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SharedPreferences preferences=getSharedPreferences(PREF_NAME,MODE_PRIVATE);
                final String email=username.getText().toString();
                final String pass=password.getText().toString();
                if(!email.isEmpty()&&!pass.isEmpty())
                {
                    final FirebaseUser user=mAuth.getCurrentUser();
                    user.sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>(){
                        @Override
                        public  void onComplete(@NonNull Task<Void> task){
                            if (task.isSuccessful()){
                                Log.d(TAG,"Email sent");
                                Toast.makeText(register.this,"Mail has been sent,Please check your mail!!",Toast.LENGTH_LONG).show();

                                if (email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                    username.setError("Enter a valid Username");
                                }
                                if (pass.length()<6||pass.isEmpty()){
                                    password.setError("Password length should be minimum 6");
                                }
                                final Task<AuthResult> authResultTask = mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {
                                                        Intent intent = new Intent(register.this, MainActivity.class);
                                                        startActivity(intent);
                                                        Toast.makeText(register.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                                        finish();


                                        } else {
                                            Log.d("SIGN UP ERROR", task.getException().toString());
                                        }

                                    }
                                });
                            }
                            else{
                                Toast.makeText(register.this, "Mail failed to send,Please check your Internet connection", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                else{
                        username.setError("Please fill the details");
                        password.setError("please fill the details");
                    }

            }
        });


    }
}
