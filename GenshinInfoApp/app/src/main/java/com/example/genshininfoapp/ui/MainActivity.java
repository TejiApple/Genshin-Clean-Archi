package com.example.genshininfoapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.genshininfoapp.R;
import com.example.genshininfoapp.ui.characters.CharactersActivity;
import com.example.genshininfoapp.ui.domains.ArtifactsActivity;
import com.example.genshininfoapp.ui.weapons.WeaponsActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Handler imageHandler = new Handler();

    int[] images = {R.drawable.bg_main_menu, R.drawable.bg_main_menu_2, R.drawable.bg_main_menu_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageHandler.post(handle);
    }

    @Override
    protected void onResume() {
        super.onResume();

        imageHandler.post(handle);

    }

    public void goToCharacters(View view) {
        intent = new Intent(this, CharactersActivity.class);
        startActivity(intent);
    }

    public void goToArtifacts(View view) {
        intent = new Intent(this, ArtifactsActivity.class);
        startActivity(intent);
    }

    public void goToWeapons(View view) {
        intent = new Intent(this, WeaponsActivity.class);
        startActivity(intent);
    }

    private final Runnable handle = this::randomBackgroundImage;

    public void randomBackgroundImage(){
        Random r = new Random();
        int i = r.nextInt(images.length);
        MainActivity.this.getWindow().setBackgroundDrawableResource(images[i]);
    }
}