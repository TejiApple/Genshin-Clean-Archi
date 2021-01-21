package com.example.genshininfoapp.ui.domains;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.genshininfoapp.R;
import com.example.genshininfoapp.adapters.ArtifactsAdapter;

import com.example.genshininfoapp.models.ArtifactsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArtifactsActivity extends AppCompatActivity {

    ListView listView;

    private ArtifactsAdapter artifactsAdapter;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artifacts);

        listView = findViewById(R.id.lvArtifacts);

        setupListview();
    }

    private void setupListview(){
        dbRef = FirebaseDatabase.getInstance().getReference();
        ArrayList<ArtifactsModel> artifactsModels = new ArrayList<>();

        dbRef.child("Artifacts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot characterSnapshot: snapshot.getChildren()){
                    ArtifactsModel value = characterSnapshot.getValue(ArtifactsModel.class);
                    artifactsModels.add(value);
                }
                artifactsAdapter = new ArtifactsAdapter(ArtifactsActivity.this, artifactsModels);
                listView.setAdapter(artifactsAdapter);
                artifactsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}