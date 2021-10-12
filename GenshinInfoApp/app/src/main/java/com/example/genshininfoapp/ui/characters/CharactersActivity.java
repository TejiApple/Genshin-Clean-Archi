package com.example.genshininfoapp.ui.characters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.genshininfoapp.adapters.CharactersAdapter;
import com.example.genshininfoapp.R;
import com.example.genshininfoapp.adapters.WeaponAdapter;
import com.example.genshininfoapp.models.CharactersModel;
import com.example.genshininfoapp.models.WeaponModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CharactersActivity extends AppCompatActivity {

    final String url = "https://genshinlist.com/api/characters";
    ListView listView;
    EditText etSearch;
    Button btnSearch;
    FloatingActionButton floatingActionButton;

    ArrayList<CharactersModel> charactersModelsList;
    private CharactersAdapter genshinCharacterAdapter;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        initViews();
        setupListview();
    }

    private void initViews(){

        etSearch = findViewById(R.id.etCharacterSearch);
        listView = findViewById(R.id.lvCharacters);
        btnSearch = findViewById(R.id.btnCharacterSearch);
        floatingActionButton = findViewById(R.id.fabScrollUp);

//        hideKeyboard(this, etSearch);
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

        btnSearch.setOnClickListener(v -> {
            String etValue = etSearch.getText().toString();

            firebaseSearch(etValue);
        });

        floatingActionButton.setOnClickListener(v -> {
            if (listView != null) {
                listView.smoothScrollToPosition(0);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > 2) {
                    floatingActionButton.setVisibility(View.VISIBLE);
                } else {
                    floatingActionButton.setVisibility(View.GONE);
                }
            }
        });
    }

    private void firebaseSearch(String etValue) {
        ArrayList<CharactersModel> characterResult = new ArrayList<>();
        for (int i = 0; i < charactersModelsList.size(); i++) {
            CharactersModel charactersModel = charactersModelsList.get(i);
            if (charactersModel.getName().toLowerCase().contains(etValue.toLowerCase())) {
                characterResult.add(charactersModel);
            }
        }
        genshinCharacterAdapter = new CharactersAdapter(this, characterResult);
        listView.setAdapter(genshinCharacterAdapter);
    }

    private void setupListview() {
        dbRef = FirebaseDatabase.getInstance().getReference();
        charactersModelsList = new ArrayList<>();

        dbRef.child("Characters").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot characterSnapshot : snapshot.getChildren()) {
                    CharactersModel value = characterSnapshot.getValue(CharactersModel.class);
                    charactersModelsList.add(value);
                }
                genshinCharacterAdapter = new CharactersAdapter(CharactersActivity.this, charactersModelsList);
                listView.setAdapter(genshinCharacterAdapter);
                genshinCharacterAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void hideKeyboard(Context context, View view){
        InputMethodManager hideKb = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        hideKb.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }
}