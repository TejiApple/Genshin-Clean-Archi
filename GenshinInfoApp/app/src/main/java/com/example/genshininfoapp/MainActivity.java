package com.example.genshininfoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToCharacters(View view) {
        intent = new Intent(this, CharactersActivity.class);
        startActivity(intent);
    }

    public void goToArtifacts(View view) {
//        intent = new Intent(this, ArtifactsActivity.class);
//        startActivity(intent);
    }

    public void goToWeapons(View view) {
//        intent = new Intent(this, WeaponsActivity.class);
//        startActivity(intent);
    }
}