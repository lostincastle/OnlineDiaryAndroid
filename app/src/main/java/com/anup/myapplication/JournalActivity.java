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
import com.anup.myapplication.models.Journal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JournalActivity extends AppCompatActivity {
    Button buttonSaveJournal, buttonBack;
    Context context;
    EditText textJournal, textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        context = this;

        buttonSaveJournal = findViewById(R.id.button_journal_save);
        buttonBack = findViewById(R.id.button_journal_back);

        textJournal = findViewById(R.id.text_input_journal);
        textTitle = findViewById(R.id.text_input_title);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonSaveJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Journal> PostJournal = ApiManager.getInstance().getApiClient()
                        .PostJournal(
                                textJournal.getText().toString(),
                                textTitle.getText().toString()
                        );
                PostJournal.enqueue(new Callback<Journal>() {
                    @Override
                    public void onResponse(Call<Journal> call, Response<Journal> response) {
                        if(response.code()==200){
                            Toast.makeText(context,"Journal Saved",Toast.LENGTH_SHORT).show();
                            textTitle.setText("");
                            textJournal.setText("");
                            startActivity(new Intent(context,MainActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(context,"Journal is not Saved",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Journal> call, Throwable t) {
                        Toast.makeText(context,"Journal Not Saved",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });




    }
}
