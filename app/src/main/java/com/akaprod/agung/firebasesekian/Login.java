package com.akaprod.agung.firebasesekian;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText txtEmail, txtPassword;
    private Button btnSignin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText)findViewById(R.id.emailMasuk);
        txtPassword = (EditText)findViewById(R.id.passwordMasuk);
        btnSignin = (Button)findViewById(R.id.signin);

        mAuth = FirebaseAuth.getInstance();

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email, password;
                email = txtEmail.getText().toString().trim();
                password = txtPassword.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    //berhasil
                                    tampilkanPesan("Berhasil Masuk");
                                } else{
                                    //gagal
                                    tampilkanPesan("Gagal Masuk");
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
