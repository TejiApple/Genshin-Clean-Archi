package com.example.genshininfoapp.ui.domains;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.genshininfoapp.R;
import com.example.genshininfoapp.adapters.ArtifactsAdapter;

import com.example.genshininfoapp.adapters.WeaponAdapter;
import com.example.genshininfoapp.models.ArtifactsModel;
import com.example.genshininfoapp.models.WeaponModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArtifactsActivity extends AppCompatActivity {

    ListView listView;
    EditText etSearch;
    Button btnSearch;
    FloatingActionButton floatingActionButton;

    private ArtifactsAdapter artifactsAdapter;
    ArrayList<ArtifactsModel> artifactsModelsList;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artifacts);

        listView = findViewById(R.id.lvArtifacts);
        etSearch = findViewById(R.id.etArtifactSearch);
        btnSearch = findViewById(R.id.btnArtifactSearch);
        floatingActionButton = findViewById(R.id.fabScrollUpArtifacts);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                firebaseSearch(s.toString());
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etValue = etSearch.getText().toString();

                firebaseSearch(etValue);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView != null){
                    listView.smoothScrollToPosition(0);
                }
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > 2){
                    floatingActionButton.setVisibility(view.VISIBLE);
                } else {
                    floatingActionButton.setVisibility(view.GONE);
                }
            }
        });
        setupListview();
    }

    private void firebaseSearch(String etValue) {
        ArrayList<ArtifactsModel> artifactResult = new ArrayList<>();
        for (int i=0; i < artifactsModelsList.size(); i++){
            ArtifactsModel artifactsModel = artifactsModelsList.get(i);
            if (artifactsModel.getName().toLowerCase().contains(etValue.toLowerCase())){
                artifactResult.add(artifactsModel);
            }
        }
        artifactsAdapter = new ArtifactsAdapter(this, artifactResult);
        listView.setAdapter(artifactsAdapter);
    }

    private void setupListview(){
        dbRef = FirebaseDatabase.getInstance().getReference();
        artifactsModelsList = new ArrayList<>();

        dbRef.child("Artifacts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot characterSnapshot: snapshot.getChildren()){
                    ArtifactsModel value = characterSnapshot.getValue(ArtifactsModel.class);
                    artifactsModelsList.add(value);
                }
                artifactsAdapter = new ArtifactsAdapter(ArtifactsActivity.this, artifactsModelsList);
                listView.setAdapter(artifactsAdapter);
                artifactsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}