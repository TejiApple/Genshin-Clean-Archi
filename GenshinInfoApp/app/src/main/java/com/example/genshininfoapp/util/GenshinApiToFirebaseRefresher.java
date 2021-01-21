package com.example.genshininfoapp.util;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.genshininfoapp.CharactersAdapter;
import com.example.genshininfoapp.R;
import com.example.genshininfoapp.models.CharactersModel;
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

public class GenshinApiToFirebaseRefresher {

    //Firebase reference

    //Delete all data in Characters
    //Delete all data in Weapons
    //Delete all data in Domains

    //Write again once
    // import androidx.annotation.NonNull;


//    public class CharactersActivity extends AppCompatActivity {

//        final String url = "https://genshinlist.com/api/characters";
//        ListView listView;
//        HashMap<Integer,String> idToImageUrlMap = new HashMap<>();
//        ArrayList<CharactersModel> characters = new ArrayList<>();
//        private CharactersAdapter genshinCharacterAdapter;
//        ArrayAdapter<String> adapter;
//
//        DatabaseReference dbRef;

//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_characters);
//
//            idToImageUrlMap.put(6,"https://static1.srcdn.com/wordpress/wp-content/uploads/2020/10/diluc-genshin-impact-1.jpg?q=50&fit=crop&w=960&h=500");
//            idToImageUrlMap.put(8,"https://www.siliconera.com/wp-content/uploads/2020/10/genshin-impact-jean-710x400.jpg");
//            idToImageUrlMap.put(10,"https://img.republicworld.com/republic-prod/stories/promolarge/xxhdpi/ib8j809rqa2mz60e_1602159530.jpeg?tr=w-1242,h-710,f-jpeg");
//            idToImageUrlMap.put(11,"https://www.gensh.in/fileadmin/Database/Characters/Klee/Character_Klee_XL.png");
//            idToImageUrlMap.put(13,"https://cdn.mos.cms.futurecdn.net/Kf7jRtb2x4TTMDwQG4Vv8M-320-80.png");
//            idToImageUrlMap.put(16,"https://assets.gamepur.com/wp-content/uploads/2020/10/12062337/best-qiqi-build-genshin-impact.jpg");
//            idToImageUrlMap.put(21,"https://oyster.ignimgs.com/mediawiki/apis.ign.com/genshin-impact/4/4d/Venti.jpg");
//            idToImageUrlMap.put(23,"https://static0.gamerantimages.com/wordpress/wp-content/uploads/2020/10/genshin-impact-xiao-cover.jpg");
//            idToImageUrlMap.put(26,"https://www.siliconera.com/wp-content/uploads/2020/11/genshin-impact-childe-boss-fight.jpg");
//            idToImageUrlMap.put(28,"https://www.siliconera.com/wp-content/uploads/2020/11/genshin-impact-zhongli-banner.jpg");
//            idToImageUrlMap.put(1,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/amber-genshin-wiki-guide.png");
//            idToImageUrlMap.put(2,"https://assets.gamepur.com/wp-content/uploads/2020/10/12085118/Genshin-Impact-Barbara-Best-Build.jpg");
//            idToImageUrlMap.put(3,"https://www.gensh.in/fileadmin/Database/Characters/Baidou/Character_Baidou_XL.png");
//            idToImageUrlMap.put(4,"https://oyster.ignimgs.com/mediawiki/apis.ign.com/genshin-impact/1/1b/Bennett.jpg");
//            idToImageUrlMap.put(5,"https://www.gematsu.com/wp-content/uploads/2020/06/Genshin-Impact_2020_06-03-20_Top.jpg");
//            idToImageUrlMap.put(7,"https://twinfinite.net/wp-content/uploads/2020/04/Genshin-Impact.jpg");
//            idToImageUrlMap.put(9,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/kaeya-genshin-wiki-guide.png");
//            idToImageUrlMap.put(12,"https://assets.gamepur.com/wp-content/uploads/2020/10/22064659/Lisa_best_build_Genshin_Impact.jpg");
//            idToImageUrlMap.put(14,"https://www.gematsu.com/wp-content/uploads/2020/03/Genshin-Impact_2020_03-05-20_Top.jpg");
//            idToImageUrlMap.put(15,"https://assets.gamepur.com/wp-content/uploads/2020/10/13085602/Noelle_best_build_Genshin_Impact.jpg");
//            idToImageUrlMap.put(17,"https://www.gematsu.com/wp-content/uploads/2019/10/Genshin-Impact_2019_10-18-19_Top.jpg");
//            idToImageUrlMap.put(18,"https://assets.gamepur.com/wp-content/uploads/2020/10/22053621/best_Sucrose_build_Genshin_Impact.jpg");
//            idToImageUrlMap.put(22,"https://www.gematsu.com/wp-content/uploads/2020/03/Genshin-Impact_2020_03-17-20_Top.jpg");
//            idToImageUrlMap.put(24,"https://imgix.bustle.com/uploads/image/2020/11/2/74798054-f09a-4c2a-848f-6df7ad33e99e-genshin-impact_2020_03-25-20_top.jpg?w=1200&h=630&fit=crop&crop=faces&fm=jpg");
//            idToImageUrlMap.put(25,"https://cdn.mos.cms.futurecdn.net/ukoeRe945daYVR236Wr9Y6.jpg");
//            idToImageUrlMap.put(27,"https://media.nichegamer.com/wp-content/uploads/2020/11/30151727/genshin-impact-11-30-20-3.jpg");
//
//            listView = findViewById(R.id.lvCharacters);
//
//            retrieveResult();
//
//        }
//
//
//        private void retrieveResult(){
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//
//
//                            try {
//                                JSONArray dataArray = new JSONArray(response);
//                                characters = new ArrayList<>();
//                                for (int i = 0; i < dataArray.length(); i++) {
//                                    dbRef = FirebaseDatabase.getInstance().getReference();
//                                    CharactersModel characterModel = new CharactersModel();
//                                    JSONObject dataobj = dataArray.getJSONObject(i);
//
//                                    int charId = dataobj.getInt("id");
//
//                                    characterModel.setId(dataobj.getString("id"));
//                                    characterModel.setName(dataobj.getString("name"));
//                                    characterModel.setDescription(dataobj.getString("description"));
//                                    characterModel.setGender(dataobj.getString("gender"));
//                                    characterModel.setBirthday(dataobj.getString("birthday"));
//                                    characterModel.setVision(dataobj.getString("vision"));
//                                    characterModel.setWeapon(dataobj.getString("weapon"));
//
//                                    String imageUrl = idToImageUrlMap.get(charId);
//                                    characterModel.setImageURL(imageUrl);
//
//                                    dbRef.child("Characters").push().setValue(characterModel);
//                                    characters.add(characterModel);
//                                }
//                                setupListview();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            //displaying the error in toast if occurrs
//                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//            // request queue
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//            requestQueue.add(stringRequest);
//        }
//
//        private void setupListview(){
////        genshinCharacterAdapter = new CharactersAdapter(this, characters);
////        listView.setAdapter(genshinCharacterAdapter);
//
//            ArrayList<CharactersModel> charactersModels = new ArrayList<>();
//
//            dbRef.child("Characters").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for(DataSnapshot characterSnapshot: snapshot.getChildren()){
//                        CharactersModel value = characterSnapshot.getValue(CharactersModel.class);
//                        charactersModels.add(value);
//                    }
//                    genshinCharacterAdapter = new CharactersAdapter(com.example.genshininfoapp.ui.characters.CharactersActivity.this, charactersModels);
//                    listView.setAdapter(genshinCharacterAdapter);
//                    genshinCharacterAdapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
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

//
//
//        }
//
//    }
}
