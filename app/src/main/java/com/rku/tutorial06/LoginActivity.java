package com.rku.tutorial06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Login check
        preferences = getSharedPreferences("college",MODE_PRIVATE);
        editor = preferences.edit();

        String valUsername = preferences.getString("username","");
        if(!valUsername.equals("")){
            Intent intent = new Intent(LoginActivity.this, Welcome.class);
            startActivity(intent);
            finish();
        }

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valUsername = edtUsername.getText().toString();
                String valPassword = edtPassword.getText().toString();
                Log.i("LoginScreen", "In onClick");


                if (!Patterns.EMAIL_ADDRESS.matcher(valUsername).matches()) {
                    Toast.makeText(LoginActivity.this, "Email format is not proper", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (valUsername.equals("admin@gmail.com") && valPassword.equals("123456")) {
                    Log.i("LoginScreen", "In onClick If");

                    editor.putString("username",valUsername);
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, Welcome.class);
                    startActivity(intent);
                    finish();

                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Username or Password is wrong.", Toast.LENGTH_SHORT).show();
                    Log.i("LoginScreen", "In onClick else");
                }

            }
        });
    }
}