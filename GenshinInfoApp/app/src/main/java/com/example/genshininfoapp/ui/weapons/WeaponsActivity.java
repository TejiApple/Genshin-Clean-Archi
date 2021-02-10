package com.example.genshininfoapp.ui.weapons;

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
import com.example.genshininfoapp.adapters.WeaponAdapter;
import com.example.genshininfoapp.models.WeaponModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class WeaponsActivity extends AppCompatActivity {

    ListView listView;
    EditText etSearch;
    Button btnSearch;
    FloatingActionButton floatingActionButton;

    private WeaponAdapter weaponAdapter;
    ArrayList<WeaponModel> weaponModelsList;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapons);

        listView = findViewById(R.id.lvWeapons);
        etSearch = findViewById(R.id.etWeaponSearch);
        btnSearch = findViewById(R.id.btnWeaponSearch);
        floatingActionButton = findViewById(R.id.fabScrollUpWeapons);

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

//        onItemClick();
    }

    private void firebaseSearch(String etValue) {
        ArrayList<WeaponModel> temp = new ArrayList<>();
        for (int i=0; i < weaponModelsList.size(); i++){
            WeaponModel weaponModel = weaponModelsList.get(i);
            if (weaponModel.getName().toLowerCase().contains(etValue.toLowerCase())){
                temp.add(weaponModel);
            }
        }
        weaponAdapter = new WeaponAdapter(this, temp);
        listView.setAdapter(weaponAdapter);
    }

    private void setupListview(){

        dbRef = FirebaseDatabase.getInstance().getReference();

        weaponModelsList = new ArrayList<>();

        dbRef.child("Weapons").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot characterSnapshot: snapshot.getChildren()){
                    WeaponModel value = characterSnapshot.getValue(WeaponModel.class);
                    weaponModelsList.add(value);
                }
                weaponAdapter = new WeaponAdapter(WeaponsActivity.this, weaponModelsList);
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