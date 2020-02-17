package com.anup.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anup.myapplication.api.ApiManager;
import com.anup.myapplication.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private final String TAG = "Register Activity";

    Button buttonSignup, buttonCancel;
    EditText textName, textContact, textEmail, textUsername, textPassword;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = this;

        textName = findViewById(R.id.signup_text_name);
        textContact = findViewById(R.id.signup_text_contact);
        textEmail= findViewById(R.id.signup_text_email);
        textUsername = findViewById(R.id.signup_text_username);
        textPassword = findViewById(R.id.signup_text_password);

        buttonCancel = findViewById(R.id.signup_button_cancel);
        buttonSignup = findViewById(R.id.signup_button);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, LoginActivity.class));
                finish();
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Result> Signup = ApiManager.getInstance().getApiClient().Signup(
                        textName.getText().toString(),
                        textContact.getText().toString(),
                        textEmail.getText().toString(),
                        textUsername.getText().toString(),
                        textPassword.getText().toString()
                );
                Signup.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if(response.code()==200){
                            Toast.makeText(context,"Login SuccessFul",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context, MainActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(context,"Login  Not SuccessFul",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Toast.makeText(context,"Unable to Connect",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
