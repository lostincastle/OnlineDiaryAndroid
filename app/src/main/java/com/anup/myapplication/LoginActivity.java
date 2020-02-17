package com.anup.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anup.myapplication.api.ApiClient;
import com.anup.myapplication.api.ApiManager;
import com.anup.myapplication.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public final static String TAG = "Login Activity";

    Context context;
    Button buttonSignup, buttonLogin;
    EditText textUsername, textPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        buttonSignup = findViewById(R.id.login_button_signup);
        buttonLogin = findViewById(R.id.button_login);
        textUsername = findViewById(R.id.login_text_username);
        textPassword = findViewById(R.id.login_text_password);
        ;

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Result> Login = ApiManager.getInstance().getApiClient().Login(textUsername.getText().toString(), textPassword.getText().toString());
                Login.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if(response.code()==200){

                            Toast.makeText(context,"Login SuccessFul",Toast.LENGTH_SHORT).show();
                            ApiManager.Cookie = response.headers().get("Set-Cookie");
                            startActivity(new Intent(context, MainActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(context,"Login  Not SuccessFul",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Toast.makeText(context,"Unable To Connect",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,RegisterActivity.class));
                finish();
            }
        });
    }
}

