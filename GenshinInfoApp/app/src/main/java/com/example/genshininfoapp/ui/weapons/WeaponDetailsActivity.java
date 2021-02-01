package com.example.genshininfoapp.ui.weapons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.genshininfoapp.R;

public class WeaponDetailsActivity extends AppCompatActivity {

    String details;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_details);

        textView = findViewById(R.id.tvSkills);

        if (savedInstanceState == null){
            Bundle weaponDetails = getIntent().getExtras();
            if (weaponDetails == null) {
                details = null;
            } else {
                details = weaponDetails.getString("skill");
            }
        } else {
            details = (String) savedInstanceState.getSerializable("skill");
            textView.setText(details);
        }
    }
}