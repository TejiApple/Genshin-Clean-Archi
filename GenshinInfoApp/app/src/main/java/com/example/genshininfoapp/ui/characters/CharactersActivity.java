package com.example.genshininfoapp.ui.characters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.genshininfoapp.adapters.CharactersAdapter;
import com.example.genshininfoapp.R;
import com.example.genshininfoapp.adapters.WeaponAdapter;
import com.example.genshininfoapp.models.CharactersModel;
import com.example.genshininfoapp.models.WeaponModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CharactersActivity extends AppCompatActivity{

    final String url = "https://genshinlist.com/api/characters";
    ListView listView;
    EditText etSearch;
    Button btnSearch;

    HashMap<Integer,String> idToImageUrlMap = new HashMap<>();
    ArrayList<CharactersModel> charactersModelsList;
    private CharactersAdapter genshinCharacterAdapter;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
//
//        idToImageUrlMap.put(6,"https://static1.srcdn.com/wordpress/wp-content/uploads/2020/10/diluc-genshin-impact-1.jpg?q=50&fit=crop&w=960&h=500");
//        idToImageUrlMap.put(8,"https://www.siliconera.com/wp-content/uploads/2020/10/genshin-impact-jean-710x400.jpg");
//        idToImageUrlMap.put(10,"https://img.republicworld.com/republic-prod/stories/promolarge/xxhdpi/ib8j809rqa2mz60e_1602159530.jpeg?tr=w-1242,h-710,f-jpeg");
//        idToImageUrlMap.put(11,"https://www.gensh.in/fileadmin/Database/Characters/Klee/Character_Klee_XL.png");
//        idToImageUrlMap.put(13,"https://cdn.mos.cms.futurecdn.net/Kf7jRtb2x4TTMDwQG4Vv8M-320-80.png");
//        idToImageUrlMap.put(16,"https://assets.gamepur.com/wp-content/uploads/2020/10/12062337/best-qiqi-build-genshin-impact.jpg");
//        idToImageUrlMap.put(21,"https://oyster.ignimgs.com/mediawiki/apis.ign.com/genshin-impact/4/4d/Venti.jpg");
//        idToImageUrlMap.put(23,"https://static0.gamerantimages.com/wordpress/wp-content/uploads/2020/10/genshin-impact-xiao-cover.jpg");
//        idToImageUrlMap.put(26,"https://www.siliconera.com/wp-content/uploads/2020/11/genshin-impact-childe-boss-fight.jpg");
//        idToImageUrlMap.put(28,"https://www.siliconera.com/wp-content/uploads/2020/11/genshin-impact-zhongli-banner.jpg");
//        idToImageUrlMap.put(1,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/amber-genshin-wiki-guide.png");
//        idToImageUrlMap.put(2,"https://assets.gamepur.com/wp-content/uploads/2020/10/12085118/Genshin-Impact-Barbara-Best-Build.jpg");
//        idToImageUrlMap.put(3,"https://www.gensh.in/fileadmin/Database/Characters/Baidou/Character_Baidou_XL.png");
//        idToImageUrlMap.put(4,"https://oyster.ignimgs.com/mediawiki/apis.ign.com/genshin-impact/1/1b/Bennett.jpg");
//        idToImageUrlMap.put(5,"https://www.gematsu.com/wp-content/uploads/2020/06/Genshin-Impact_2020_06-03-20_Top.jpg");
//        idToImageUrlMap.put(7,"https://twinfinite.net/wp-content/uploads/2020/04/Genshin-Impact.jpg");
//        idToImageUrlMap.put(9,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/kaeya-genshin-wiki-guide.png");
//        idToImageUrlMap.put(12,"https://assets.gamepur.com/wp-content/uploads/2020/10/22064659/Lisa_best_build_Genshin_Impact.jpg");
//        idToImageUrlMap.put(14,"https://www.gematsu.com/wp-content/uploads/2020/03/Genshin-Impact_2020_03-05-20_Top.jpg");
//        idToImageUrlMap.put(15,"https://assets.gamepur.com/wp-content/uploads/2020/10/13085602/Noelle_best_build_Genshin_Impact.jpg");
//        idToImageUrlMap.put(17,"https://www.gematsu.com/wp-content/uploads/2019/10/Genshin-Impact_2019_10-18-19_Top.jpg");
//        idToImageUrlMap.put(18,"https://assets.gamepur.com/wp-content/uploads/2020/10/22053621/best_Sucrose_build_Genshin_Impact.jpg");
//        idToImageUrlMap.put(22,"https://www.gematsu.com/wp-content/uploads/2020/03/Genshin-Impact_2020_03-17-20_Top.jpg");
//        idToImageUrlMap.put(24,"https://imgix.bustle.com/uploads/image/2020/11/2/74798054-f09a-4c2a-848f-6df7ad33e99e-genshin-impact_2020_03-25-20_top.jpg?w=1200&h=630&fit=crop&crop=faces&fm=jpg");
//        idToImageUrlMap.put(25,"https://cdn.mos.cms.futurecdn.net/ukoeRe945daYVR236Wr9Y6.jpg");
//        idToImageUrlMap.put(27,"https://media.nichegamer.com/wp-content/uploads/2020/11/30151727/genshin-impact-11-30-20-3.jpg");

        etSearch = findViewById(R.id.etCharacterSearch);
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
        btnSearch = findViewById(R.id.btnCharacterSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etValue = etSearch.getText().toString();

                firebaseSearch(etValue);
            }
        });

        listView = findViewById(R.id.lvCharacters);

//        retrieveResult();
        setupListview();
    }

    private void firebaseSearch(String etValue) {
        ArrayList<CharactersModel> characterResult = new ArrayList<>();
        for (int i=0; i < charactersModelsList.size(); i++){
            CharactersModel charactersModel = charactersModelsList.get(i);
            if (charactersModel.getName().toLowerCase().contains(etValue.toLowerCase())){
                characterResult.add(charactersModel);
            }
        }
        genshinCharacterAdapter = new CharactersAdapter(this, characterResult);
        listView.setAdapter(genshinCharacterAdapter);
    }


//
//    private void retrieveResult(){
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//
//
//                        try {
//                            JSONArray dataArray = new JSONArray(response);
//                            characters = new ArrayList<>();
//                            for (int i = 0; i < dataArray.length(); i++) {
//                                dbRef = FirebaseDatabase.getInstance().getReference();
//                                CharactersModel characterModel = new CharactersModel();
//                                JSONObject dataobj = dataArray.getJSONObject(i);
//
//                                int charId = dataobj.getInt("id");
//
//                                characterModel.setId(dataobj.getString("id"));
//                                characterModel.setName(dataobj.getString("name"));
//                                characterModel.setDescription(dataobj.getString("description"));
//                                characterModel.setGender(dataobj.getString("gender"));
//                                characterModel.setBirthday(dataobj.getString("birthday"));
//                                characterModel.setVision(dataobj.getString("vision"));
//                                characterModel.setWeapon(dataobj.getString("weapon"));
//
//                                String imageUrl = idToImageUrlMap.get(charId);
//                                characterModel.setImageURL(imageUrl);
//
//                                dbRef.child("Characters").push().setValue(characterModel);
//                                characters.add(characterModel);
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
//        genshinCharacterAdapter = new CharactersAdapter(this, characters);
//        listView.setAdapter(genshinCharacterAdapter);
        dbRef = FirebaseDatabase.getInstance().getReference();
       charactersModelsList = new ArrayList<>();

            dbRef.child("Characters").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot characterSnapshot: snapshot.getChildren()){
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
//        dbRef.child("Characters").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                CharactersModel value = snapshot.getValue(CharactersModel.class);
//                characters.add(value);
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



    }

}