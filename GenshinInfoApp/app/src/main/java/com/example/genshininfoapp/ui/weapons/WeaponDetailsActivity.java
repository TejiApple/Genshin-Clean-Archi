package com.example.genshininfoapp.ui.weapons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.genshininfoapp.R;
import com.example.genshininfoapp.models.WeaponDetailsModel;
import com.google.gson.Gson;

public class WeaponDetailsActivity extends AppCompatActivity {

    String details;
    TextView textView;
    WeaponDetailsModel weaponDetailsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_details);

        textView = findViewById(R.id.tvSkills);

        Intent intent = getIntent();
//        Gson gson = new Gson();

        if (intent != null){
            Bundle weaponDetails = intent.getExtras();
            if (weaponDetails != null) {
                details = weaponDetails.getString("skill");
//                weaponDetailsModel = gson.fromJson(details, WeaponDetailsModel.class);
                textView.setText(details);
                Log.v("GOT IT", "HELLO MADAFAKA");
            }
        }
    }
}