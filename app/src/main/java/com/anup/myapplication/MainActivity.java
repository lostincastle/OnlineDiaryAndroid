package com.anup.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anup.myapplication.api.ApiManager;
import com.anup.myapplication.models.Journal;

import java.util.List;

import javax.xml.transform.Templates;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button buttonAddJournal, buttonAddVault;
    Context context;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAddJournal = findViewById(R.id.button_add_journal);
        buttonAddVault = findViewById(R.id.button_vault);
        context = this;

        recyclerView = findViewById(R.id.journallist_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        LoadJournalList();


        buttonAddJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,JournalActivity.class));
                finish();
            }
        });

        buttonAddVault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void LoadJournalList() {
        Call<List<Journal>> getJournals = ApiManager.getInstance().getApiClient().getJournals(ApiManager.Cookie);
        getJournals.enqueue(new Callback<List<Journal>>() {
            @Override
            public void onResponse(Call<List<Journal>> call, Response<List<Journal>> response) {
                if(response.code()==200){
                    recyclerView.setAdapter(new JournalListAdpater(context,response.body()));
                }else {
                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Journal>> call, Throwable t) {
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class JournalListAdpater extends RecyclerView.Adapter<JournalViewHolder> {
        private final Context context;
        private final List<Journal> journals;

        public JournalListAdpater(Context context, List<Journal> body) {
            this.context = context;
            this.journals = body;
        }

        @NonNull
        @Override
        public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.viewholder_journal,parent,false);
            return new JournalViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull JournalViewHolder holder, int position) {
            holder.getTextViewTitle().setText(journals.get(position).getName());
            holder.getTextViewContents().setText(journals.get(position).getInfo());

        }

        @Override
        public int getItemCount() {
            return journals.size();
        }
    }

    private class JournalViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewContents;
        public JournalViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.viewholder_title);
            textViewContents = itemView.findViewById(R.id.viewholder_contents);
        }

        public TextView getTextViewTitle() {
            return textViewTitle;
        }

        public void setTextViewTitle(TextView textViewTitle) {
            this.textViewTitle = textViewTitle;
        }

        public TextView getTextViewContents() {
            return textViewContents;
        }

        public void setTextViewContents(TextView textViewContents) {
            this.textViewContents = textViewContents;
        }
    }
}
