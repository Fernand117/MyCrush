package com.ferdev.mycrush;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText txtEmail, txtPassword;
    private Button btnLogin, btnLoginFacebook, btnRegistrar;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        txtEmail = (TextInputEditText) findViewById(R.id.txtEmail);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLoginFacebook = findViewById(R.id.btnLoginFacebook);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm(txtEmail.getText().toString(), txtPassword.getText().toString());
            }
        });
    }

    private void validateForm(String email, String password){
        if (email.isEmpty()){
            toastMessage("Ingrese su correo por favor");
            txtEmail.setText("");
            txtEmail.setFocusable(true);
        } else if (password.isEmpty()){
            toastMessage("Ingrese su contraseña por favor");
            txtPassword.setText("");
            txtEmail.setFocusable(true);
        } else if (email.isEmpty() && password.isEmpty()){
            toastMessage("Por favor, ingrese su correo y contraseña.");
            txtEmail.setText("");
            txtPassword.setText("");
            txtEmail.setFocusable(true);
        } else {
            new loginAsync().execute();
        }
    }

    private void toastMessage(String mensaje){
        Toast.makeText(LoginActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private class loginAsync extends AsyncTask<String, String, JSONObject>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setTitle("Cargando...");
            progressDialog.setMessage("Iniciando sesion, espere por favor...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if (progressDialog != null && progressDialog.isShowing()){
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}