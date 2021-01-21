package com.example.genshininfoapp.ui.weapons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.genshininfoapp.R;
import com.example.genshininfoapp.adapters.CharactersAdapter;
import com.example.genshininfoapp.adapters.WeaponAdapter;
import com.example.genshininfoapp.models.CharactersModel;
import com.example.genshininfoapp.models.WeaponModel;
import com.example.genshininfoapp.ui.characters.CharactersActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class WeaponsActivity extends AppCompatActivity {


    final String url = "https://genshinlist.com/api/weapons";
    ListView listView;
    ArrayList<WeaponModel> weapon;
    private WeaponAdapter weaponAdapter;

    HashMap<Integer,String> idToImageUrlMap = new HashMap<>();

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapons);

//
//        idToImageUrlMap.put(72,"https://static.wikia.nocookie.net/gensin-impact/images/8/80/Weapon_Primordial_Jade_Winged-Spear.png/revision/latest/top-crop/width/360/height/450?cb=20201116152024");
//        idToImageUrlMap.put(74,"https://static.wikia.nocookie.net/gensin-impact/images/6/69/Weapon_Skyward_Spine.png/revision/latest/top-crop/width/360/height/360?cb=20201116035301");
//        idToImageUrlMap.put(76,"https://static.wikia.nocookie.net/gensin-impact/images/6/6a/Weapon_Aquila_Favonia.png/revision/latest/top-crop/width/360/height/450?cb=20201120002750");
//        idToImageUrlMap.put(2,"https://static.wikia.nocookie.net/gensin-impact/images/d/de/Weapon_Amos%27_Bow.png/revision/latest?cb=20201120010513");
//        idToImageUrlMap.put(60,"https://static.wikia.nocookie.net/gensin-impact/images/4/4f/Weapon_Wolf%27s_Gravestone.png/revision/latest/top-crop/width/360/height/360?cb=20201116035623");
//        idToImageUrlMap.put(70,"https://zilliongamer.com/uploads/genshin-impact/weapon-list/polearms-list/kunwos-iris-rift-genshin-impact.jpg");
//        idToImageUrlMap.put(17,"https://static.wikia.nocookie.net/gensin-impact/images/1/19/Weapon_Skyward_Harp.png/revision/latest/top-crop/width/360/height/360?cb=20201116035246");
//        idToImageUrlMap.put(35,"https://static.wikia.nocookie.net/gensin-impact/images/3/33/Weapon_Skyward_Atlas.png/revision/latest?cb=20201116035225");
//        idToImageUrlMap.put(55,"https://static.wikia.nocookie.net/gensin-impact/images/0/0b/Weapon_Skyward_Pride.png/revision/latest/top-crop/width/360/height/360?cb=20201116035255");
//        idToImageUrlMap.put(3,"https://static.wikia.nocookie.net/gensin-impact/images/b/b8/Weapon_Blackcliff_Warbow.png/revision/latest?cb=20201103093753");
//        idToImageUrlMap.put(27,"https://static.wikia.nocookie.net/gensin-impact/images/9/98/Weapon_Lost_Prayer_to_the_Sacred_Winds.png/revision/latest/top-crop/width/360/height/360?cb=20201116034132");
//        idToImageUrlMap.put(29,"https://static.wikia.nocookie.net/gensin-impact/images/9/9f/Weapon_Mappa_Mare_2nd_Ascension.png/revision/latest?cb=20201028115900");
//        idToImageUrlMap.put(33,"https://static.wikia.nocookie.net/gensin-impact/images/9/99/Weapon_Royal_Grimoire.png/revision/latest/top-crop/width/360/height/360?cb=20201120000114");
//        idToImageUrlMap.put(48,"https://static.wikia.nocookie.net/gensin-impact/images/a/ab/Weapon_Prototype_Archaic.png/revision/latest?cb=20201116034721");
//        idToImageUrlMap.put(52,"https://static.wikia.nocookie.net/gensin-impact/images/1/17/Weapon_Sacrificial_Greatsword.png/revision/latest/top-crop/width/360/height/450?cb=20201120004023");
//        idToImageUrlMap.put(64,"https://static.wikia.nocookie.net/gensin-impact/images/4/4c/Weapon_Crescent_Pike.png/revision/latest/top-crop/width/360/height/360?cb=20201116033544");
//        idToImageUrlMap.put(67,"https://static.wikia.nocookie.net/gensin-impact/images/5/57/Weapon_Favonius_Lance.png/revision/latest/top-crop/width/360/height/360?cb=20201116154512");
//        idToImageUrlMap.put(77,"https://static.wikia.nocookie.net/gensin-impact/images/6/6f/Weapon_Blackcliff_Longsword.png/revision/latest?cb=20201116033216");
//        idToImageUrlMap.put(86,"https://static.wikia.nocookie.net/gensin-impact/images/f/fa/Weapon_Prototype_Rancour_2nd.png/revision/latest/top-crop/width/300/height/300?cb=20201119235212");
//        idToImageUrlMap.put(91,"https://static.wikia.nocookie.net/gensin-impact/images/0/03/Weapon_Skyward_Blade.png/revision/latest?cb=20201116035239");
//        idToImageUrlMap.put(92,"https://static.wikia.nocookie.net/gensin-impact/images/8/83/Weapon_The_Alley_Flash.png/revision/latest/top-crop/width/360/height/450?cb=20201016011557");
//        idToImageUrlMap.put(14,"https://static.wikia.nocookie.net/gensin-impact/images/e/ec/Weapon_Sacrificial_Bow.png/revision/latest?cb=20201012024723");
//        idToImageUrlMap.put(51,"https://static.wikia.nocookie.net/gensin-impact/images/b/bf/Weapon_Royal_Greatsword.png/revision/latest/top-crop/width/360/height/360?cb=20201116034928");
//        idToImageUrlMap.put(9,"https://static.wikia.nocookie.net/gensin-impact/images/4/43/Weapon_Prototype_Crescent.png/revision/latest?cb=20201116034737");
//        idToImageUrlMap.put(12,"https://static.wikia.nocookie.net/gensin-impact/images/9/99/Weapon_Royal_Bow.png/revision/latest?cb=20201120002134");
//        idToImageUrlMap.put(13,"https://static.wikia.nocookie.net/gensin-impact/images/1/1c/Weapon_Rust.png/revision/latest/top-crop/width/360/height/360?cb=20201120002437");
//        idToImageUrlMap.put(19,"https://static.wikia.nocookie.net/gensin-impact/images/7/71/Weapon_The_Stringless.png/revision/latest/top-crop/width/360/height/450?cb=20201116035406");
//        idToImageUrlMap.put(20,"https://static.wikia.nocookie.net/gensin-impact/images/f/ff/Weapon_The_Viridescent_Hunt.png/revision/latest/top-crop/width/360/height/360?cb=20201120010331");
//        idToImageUrlMap.put(23,"https://static.wikia.nocookie.net/gensin-impact/images/a/a6/Weapon_Blackcliff_Agate.png/revision/latest/top-crop/width/360/height/360?cb=20201119233950");
//        idToImageUrlMap.put(26,"https://static.wikia.nocookie.net/gensin-impact/images/3/36/Weapon_Favonius_Codex.png/revision/latest/top-crop/width/360/height/360?cb=20201116033719");
//        idToImageUrlMap.put(32,"https://static.wikia.nocookie.net/gensin-impact/images/c/c3/Weapon_Prototype_Malice_2nd.png/revision/latest/top-crop/width/300/height/300?cb=20201119204209");
//        idToImageUrlMap.put(36,"https://static.wikia.nocookie.net/gensin-impact/images/f/fc/Weapon_Solar_Pearl.png/revision/latest/top-crop/width/360/height/450?cb=20201116035322");
//        idToImageUrlMap.put(37,"https://static.wikia.nocookie.net/gensin-impact/images/f/f0/Weapon_The_Widsith.png/revision/latest/top-crop/width/360/height/360?cb=20201119201814");
//        idToImageUrlMap.put(40,"https://static.wikia.nocookie.net/gensin-impact/images/c/c6/Weapon_Wine_and_Song.png/revision/latest/top-crop/width/360/height/360?cb=20200715012327");
//        idToImageUrlMap.put(41,"https://static.wikia.nocookie.net/gensin-impact/images/3/31/Weapon_Blackcliff_Slasher_2nd_3D.png/revision/latest?cb=20201103224700");
//        idToImageUrlMap.put(50,"https://static.wikia.nocookie.net/gensin-impact/images/d/d4/Weapon_Rainslasher.png/revision/latest?cb=20201119235128");
//        idToImageUrlMap.put(53,"https://static.wikia.nocookie.net/gensin-impact/images/8/88/Weapon_Serpent_Spine.png/revision/latest?cb=20201116035126");
//        idToImageUrlMap.put(56,"https://static.wikia.nocookie.net/gensin-impact/images/6/6e/Weapon_The_Bell.png/revision/latest?cb=20201116035344");
//        idToImageUrlMap.put(59,"https://static.wikia.nocookie.net/gensin-impact/images/0/04/Weapon_Whiteblind.png/revision/latest?cb=20200620082127");
//        idToImageUrlMap.put(63,"https://static.wikia.nocookie.net/gensin-impact/images/d/d5/Weapon_Blackcliff_Pole.png/revision/latest/top-crop/width/360/height/360?cb=20201116153435");
//        idToImageUrlMap.put(71,"https://static.wikia.nocookie.net/gensin-impact/images/2/2a/Weapon_Lithic_Spear.png/revision/latest/top-crop/width/360/height/360?cb=20201116042459");
//        idToImageUrlMap.put(73,"https://static.wikia.nocookie.net/gensin-impact/images/5/56/Weapon_Prototype_Grudge_2nd.png/revision/latest/top-crop/width/300/height/300?cb=20201116154402");
//        idToImageUrlMap.put(84,"https://static.wikia.nocookie.net/gensin-impact/images/3/35/Weapon_Iron_Sting.png/revision/latest?cb=20201116034058");
//        idToImageUrlMap.put(85,"https://static.wikia.nocookie.net/gensin-impact/images/0/09/Weapon_Lion%27s_Roar_2nd.png/revision/latest/top-crop/width/300/height/300?cb=20201119232858");
//        idToImageUrlMap.put(87,"https://static.wikia.nocookie.net/gensin-impact/images/c/cd/Weapon_Royal_Longsword.png/revision/latest?cb=20201116034952");
//        idToImageUrlMap.put(93,"https://static.wikia.nocookie.net/gensin-impact/images/c/cf/Weapon_The_Black_Sword.png/revision/latest/top-crop/width/360/height/360?cb=20201116035352");
//        idToImageUrlMap.put(94,"https://static.wikia.nocookie.net/gensin-impact/images/6/63/Weapon_The_Flute.png/revision/latest/top-crop/width/360/height/360?cb=20201119203316");
//        idToImageUrlMap.put(1,"https://static.wikia.nocookie.net/gensin-impact/images/0/0a/Weapon_Alley_Hunter.png/revision/latest/top-crop/width/360/height/360?cb=20210111041718");
//        idToImageUrlMap.put(4,"https://static.wikia.nocookie.net/gensin-impact/images/3/32/Weapon_Compound_Bow.png/revision/latest/top-crop/width/360/height/360?cb=20201116033506");
//        idToImageUrlMap.put(6,"https://static.wikia.nocookie.net/gensin-impact/images/8/85/Weapon_Favonius_Warbow.png/revision/latest?cb=20201120003145");
//        idToImageUrlMap.put(25,"data:image/jpeg;base64,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");
//        idToImageUrlMap.put(34,"https://static.wikia.nocookie.net/gensin-impact/images/6/6c/Weapon_Sacrificial_Fragments.png/revision/latest/scale-to-width-down/340?cb=20201116035037");
//        idToImageUrlMap.put(44,"https://static.wikia.nocookie.net/gensin-impact/images/9/9c/Weapon_Favonius_Greatsword.png/revision/latest?cb=20201119235934");
//        idToImageUrlMap.put(46,"https://static.wikia.nocookie.net/gensin-impact/images/3/3a/Weapon_Lithic_Blade.png/revision/latest/top-crop/width/360/height/360?cb=20200716172440");
//        idToImageUrlMap.put(65,"https://static.wikia.nocookie.net/gensin-impact/images/6/69/Weapon_Deathmatch.png/revision/latest/top-crop/width/360/height/450?cb=20201116154647");
//        idToImageUrlMap.put(66,"https://static.wikia.nocookie.net/gensin-impact/images/2/24/Weapon_Dragon%27s_Bane.png/revision/latest?cb=20201116033629");
//        idToImageUrlMap.put(81,"https://static.wikia.nocookie.net/gensin-impact/images/9/90/Weapon_Favonius_Sword.png/revision/latest/top-crop/width/360/height/360?cb=20201116033811");
//        idToImageUrlMap.put(88,"https://static.wikia.nocookie.net/gensin-impact/images/a/a0/Weapon_Sacrificial_Sword.png/revision/latest?cb=20201120010840");
//        idToImageUrlMap.put(5,"https://static.wikia.nocookie.net/gensin-impact/images/a/a1/Weapon_Ebony_Bow.png/revision/latest?cb=20200619005942");
//        idToImageUrlMap.put(8,"https://static.wikia.nocookie.net/gensin-impact/images/3/38/Weapon_Messenger.png/revision/latest?cb=20201116034227");
//        idToImageUrlMap.put(10,"https://static.wikia.nocookie.net/gensin-impact/images/d/d0/Weapon_Raven_Bow.png/revision/latest/top-crop/width/360/height/360?cb=20201116034840");
//        idToImageUrlMap.put(21,"https://static.wikia.nocookie.net/gensin-impact/images/9/97/Weapon_Amber_Catalyst.png/revision/latest/top-crop/width/360/height/450?cb=20200619021800");
//        idToImageUrlMap.put(24,"https://static.wikia.nocookie.net/gensin-impact/images/2/27/Weapon_Emerald_Orb_3D.png/revision/latest?cb=20201010202456");
//        idToImageUrlMap.put(39,"https://static.wikia.nocookie.net/gensin-impact/images/e/e3/Weapon_Twin_Nephrite.png/revision/latest/top-crop/width/360/height/360?cb=20201119201412");
//        idToImageUrlMap.put(49,"https://static.wikia.nocookie.net/gensin-impact/images/7/71/Weapon_Quartz.png/revision/latest?cb=20200620071952");
//        idToImageUrlMap.put(68,"https://static.wikia.nocookie.net/gensin-impact/images/4/41/Weapon_Halberd.png/revision/latest/top-crop/width/360/height/360?cb=20201116033956");
//        idToImageUrlMap.put(79,"https://static.wikia.nocookie.net/gensin-impact/images/3/3a/Weapon_Dark_Iron_Sword.png/revision/latest?cb=20201119235556");
//        idToImageUrlMap.put(95,"https://static.wikia.nocookie.net/gensin-impact/images/c/c9/Weapon_Traveler%27s_Handy_Sword.png/revision/latest?cb=20201116035456");
//        idToImageUrlMap.put(16,"https://static.wikia.nocookie.net/gensin-impact/images/5/52/Weapon_Sharpshooter%27s_Oath.png/revision/latest/top-crop/width/360/height/360?cb=20201116035135");
//        idToImageUrlMap.put(30,"https://static.wikia.nocookie.net/gensin-impact/images/1/11/Weapon_Otherworldly_Story.png/revision/latest/top-crop/width/360/height/360?cb=20201116034636");
//        idToImageUrlMap.put(38,"https://static.wikia.nocookie.net/gensin-impact/images/1/19/Weapon_Thrilling_Tales_of_Dragon_Slayers.png/revision/latest/top-crop/width/360/height/360?cb=20201119201736");
//        idToImageUrlMap.put(43,"https://static.wikia.nocookie.net/gensin-impact/images/7/74/Weapon_Debate_Club.png/revision/latest/top-crop/width/360/height/360?cb=20201116033616");
//        idToImageUrlMap.put(45,"https://static.wikia.nocookie.net/gensin-impact/images/e/e9/Weapon_Ferrous_Shadow.png/revision/latest?cb=20201120003242");
//        idToImageUrlMap.put(54,"https://static.wikia.nocookie.net/gensin-impact/images/6/6e/Weapon_Skyrider_Greatsword.png/revision/latest?cb=20200620073049");
//        idToImageUrlMap.put(58,"https://static.wikia.nocookie.net/gensin-impact/images/7/77/Weapon_White_Iron_Greatsword_2nd_3D.png/revision/latest?cb=20201011184131");
//        idToImageUrlMap.put(75,"https://static.wikia.nocookie.net/gensin-impact/images/1/1f/Weapon_White_Tassel.png/revision/latest?cb=20201011171957");
//        idToImageUrlMap.put(78,"https://static.wikia.nocookie.net/gensin-impact/images/9/9c/Weapon_Cool_Steel.png/revision/latest/top-crop/width/360/height/360?cb=20201119233444");
//        idToImageUrlMap.put(82,"https://static.wikia.nocookie.net/gensin-impact/images/f/f7/Weapon_Fillet_Blade.png/revision/latest/top-crop/width/360/height/450?cb=20201116033941");
//        idToImageUrlMap.put(83,"https://static.wikia.nocookie.net/gensin-impact/images/2/23/Weapon_Harbinger_of_Dawn.png/revision/latest?cb=20201119233056");
//        idToImageUrlMap.put(11,"https://static.wikia.nocookie.net/gensin-impact/images/b/b5/Weapon_Recurve_Bow.png/revision/latest/top-crop/width/360/height/360?cb=20201120005927");
//        idToImageUrlMap.put(18,"https://static.wikia.nocookie.net/gensin-impact/images/b/be/Weapon_Slingshot_2nd_3D.png/revision/latest?cb=20201011192031");
//        idToImageUrlMap.put(28,"https://static.wikia.nocookie.net/gensin-impact/images/3/39/Weapon_Magic_Guide.png/revision/latest/top-crop/width/360/height/360?cb=20201119232047");
//        idToImageUrlMap.put(42,"https://static.wikia.nocookie.net/gensin-impact/images/4/4a/Weapon_Bloodtainted_Greatsword.png/revision/latest?cb=20201119233531");
//        idToImageUrlMap.put(62,"https://static.wikia.nocookie.net/gensin-impact/images/4/43/Weapon_Black_Tassel.png/revision/latest?cb=20201116033134");
//        idToImageUrlMap.put(90,"https://static.wikia.nocookie.net/gensin-impact/images/3/34/Weapon_Skyrider_Sword.png/revision/latest/top-crop/width/360/height/360?cb=20201116035206");
//        idToImageUrlMap.put(15,"https://static.wikia.nocookie.net/gensin-impact/images/8/82/Weapon_Seasoned_Hunter%27s_Bow.png/revision/latest?cb=20201116035113");
//        idToImageUrlMap.put(31,"https://static.wikia.nocookie.net/gensin-impact/images/1/16/Weapon_Pocket_Grimoire.png/revision/latest/top-crop/width/360/height/360?cb=20201119204545");
//        idToImageUrlMap.put(47,"https://static.wikia.nocookie.net/gensin-impact/images/0/0b/Weapon_Old_Merc%27s_Pal.png/revision/latest/top-crop/width/360/height/450?cb=20201116034249");
//        idToImageUrlMap.put(69,"https://static.wikia.nocookie.net/gensin-impact/images/2/25/Weapon_Iron_Point.png/revision/latest/top-crop/width/360/height/360?cb=20201116034039");
//        idToImageUrlMap.put(89,"https://static.wikia.nocookie.net/gensin-impact/images/3/32/Weapon_Silver_Sword.png/revision/latest?cb=20201116035150");
//        idToImageUrlMap.put(7,"https://static.wikia.nocookie.net/gensin-impact/images/4/44/Weapon_Hunter%27s_Bow.png/revision/latest?cb=20201116034023");
//        idToImageUrlMap.put(22,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/apprentice-notes-catalyst-weapon-genshin-impact-wiki-guide.png");
//        idToImageUrlMap.put(57,"https://static.wikia.nocookie.net/gensin-impact/images/4/4c/Weapon_Waster_Greatsword.png/revision/latest?cb=20201120001015");
//        idToImageUrlMap.put(61,"https://static.wikia.nocookie.net/gensin-impact/images/f/fc/Weapon_Beginner%27s_Protector.png/revision/latest?cb=20201116033115");
//        idToImageUrlMap.put(80,"https://static.wikia.nocookie.net/gensin-impact/images/2/2f/Weapon_Dull_Blade.png/revision/latest?cb=20201119235841");

        listView = findViewById(R.id.lvWeapons);

//        retrieveResult();
        setupListview();
    }

//
//    private void retrieveResult(){
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONArray dataArray = new JSONArray(response);
//                            weapon = new ArrayList<>();
//                            for (int i = 0; i < dataArray.length(); i++) {
//                                dbRef = FirebaseDatabase.getInstance().getReference();
//                                WeaponModel weaponModel = new WeaponModel();
//                                JSONObject dataobj = dataArray.getJSONObject(i);
//
//                                int charId = dataobj.getInt("id");
//                                weaponModel.setName(dataobj.getString("name"));
//                                weaponModel.setType(dataobj.getString("type"));
//                                weaponModel.setRarity(dataobj.getString("rarity"));
//
//
//                                String imageUrl = idToImageUrlMap.get(charId);
//                                weaponModel.setImageURL(imageUrl);
//
//                                dbRef.child("Weapons").push().setValue(weaponModel);
//                                weapon.add(weaponModel);
//                            }
//                            setupListview();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //displaying the error in toast if occurrs
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        // request queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        requestQueue.add(stringRequest);
//    }

    private void setupListview(){
//        weaponAdapter = new WeaponAdapter(this, weapon);
//        listView.setAdapter(weaponAdapter);
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

}