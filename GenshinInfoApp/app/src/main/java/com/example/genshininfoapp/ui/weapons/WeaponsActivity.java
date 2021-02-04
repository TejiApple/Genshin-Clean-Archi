package com.example.genshininfoapp.ui.weapons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.genshininfoapp.R;
import com.example.genshininfoapp.adapters.WeaponAdapter;
import com.example.genshininfoapp.models.WeaponDetailsModel;
import com.example.genshininfoapp.models.WeaponModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class WeaponsActivity extends AppCompatActivity {

    ListView listView;
    EditText etSearch;
    Button btnSearch;

    private WeaponAdapter weaponAdapter;
    ArrayList<WeaponDetailsModel> arrayAdapter;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapons);

        listView = findViewById(R.id.lvWeapons);

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etValue = etSearch.getText().toString();

                firebaseSearch(etValue);
            }
        });

        setupListview();

//        onItemClick();
    }

    private void firebaseSearch(String etValue) {
        dbRef = FirebaseDatabase.getInstance().getReference("Weapons");
        Query firebaseSearch = dbRef.orderByChild("name").startAt(etValue).endAt(etValue + "\uf8ff");
        
    }

    private void setupListview(){

        dbRef = FirebaseDatabase.getInstance().getReference();

        ArrayList<WeaponModel> weaponModels = new ArrayList<>();

        dbRef.child("Weapons").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot characterSnapshot: snapshot.getChildren()){
                    WeaponModel value = characterSnapshot.getValue(WeaponModel.class);
                    weaponModels.add(value);
                }
                weaponAdapter = new WeaponAdapter(WeaponsActivity.this, weaponModels);
                listView.setAdapter(weaponAdapter);
                weaponAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    public void onItemClick(){
//        dbRef = FirebaseDatabase.getInstance().getReference();
//
//        ArrayList<WeaponDetailsModel> weaponDetails = new ArrayList<>();
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                dbRef.child("Weapon Details").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
////                        for(DataSnapshot weaponDetail: snapshot.getChildren()){
////                            WeaponDetailsModel value = weaponDetail.getValue(WeaponDetailsModel.class);
////                            weaponDetails.add(value);
////                        }
//
//                            WeaponDetailsModel details = arrayAdapter.get(position);
//                            Intent intent = new Intent(WeaponsActivity.this, WeaponDetailsActivity.class);
////                            listView.setAdapter(arrayAdapter);
//                            intent.putExtra("skill", details);
//                            startActivity(intent);
//                        }
//
//                    @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                   }
//               });
//            }
//      });
//    }
}