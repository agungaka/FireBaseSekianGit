package com.akaprod.agung.firebasesekian;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText txtEmail, txtPassword;
    private Button btnSignup;
    private TextView txtSigninPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = (EditText)findViewById(R.id.email);
        txtPassword = (EditText)findViewById(R.id.password);
        btnSignup = (Button)findViewById(R.id.signup);
        txtSigninPage = (TextView)findViewById(R.id.signinPage);

        //deklarasi authentication
        mAuth = FirebaseAuth.getInstance();

        txtSigninPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //action
                String email, password;
                email = txtEmail.getText().toString().trim();
                password = txtPassword.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    //berhasil
                                    tampilkanPesan("Berhasil daftar");
                                } else{
                                    //gagal
                                    tampilkanPesan("Gagal daftar");
                                }

                            }
                        });
            }
        });
    }

    private void tampilkanPesan(String isi){
        Toast.makeText(getApplicationContext(),isi,Toast.LENGTH_LONG).show();
    }

}
