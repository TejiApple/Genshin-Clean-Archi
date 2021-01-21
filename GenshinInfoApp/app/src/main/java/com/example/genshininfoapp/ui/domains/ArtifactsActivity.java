package com.example.genshininfoapp.ui.domains;

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
import com.example.genshininfoapp.adapters.ArtifactsAdapter;
import com.example.genshininfoapp.adapters.CharactersAdapter;
import com.example.genshininfoapp.models.ArtifactsModel;
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

public class ArtifactsActivity extends AppCompatActivity {

    final String url = "https://genshinlist.com/api/artifacts";
    ListView listView;
    ArrayList<ArtifactsModel> artifacts;
    private ArtifactsAdapter artifactsAdapter;

    HashMap<Integer,String> idToImageUrlMap = new HashMap<>();

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artifacts);

//
//        idToImageUrlMap.put(27,"https://www.mrguider.org/wp-content/uploads/2020/10/Wanderers-Troupe-Artifact-Guide-Genshin-Impact.jpg");
//        idToImageUrlMap.put(26,"https://img.game8.co/3280457/4d78d3268dd3717489047b20e7a68176.png/show");
//        idToImageUrlMap.put(25,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/traveling_doctor-genshin-wiki-guide.jpg");
//        idToImageUrlMap.put(24,"https://www.mrguider.org/wp-content/uploads/2020/10/Tiny-Miracle-Artifact-Guide-Genshin-Impact.jpg");
//        idToImageUrlMap.put(23,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/thundersoother-genshin-wiki-guide.jpg");
//        idToImageUrlMap.put(22,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRI1qqkNBRkAmceVEPNF93yCOpn7jRh3fjw_g&usqp=CAU");
//        idToImageUrlMap.put(21,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/the_exile-genshin-wiki-guide.jpg");
//        idToImageUrlMap.put(20,"https://static.wikia.nocookie.net/gensin-impact/images/e/e5/Item_Scholar%27s_Bookmark.png/revision/latest/scale-to-width-down/340?cb=20201120063103");
//        idToImageUrlMap.put(19,"https://static.wikia.nocookie.net/gensin-impact/images/a/a6/Item_Summer_Night%27s_Bloom.png/revision/latest?cb=20201120064133");
//        idToImageUrlMap.put(18,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/resolution_of_sojourner-genshin-wiki-guide.jpg");
//        idToImageUrlMap.put(17,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRL3DcmhpYjpsoNiwGReLQsbmXkun3JL8-w0A&usqp=CAU");
//        idToImageUrlMap.put(16,"https://static.wikia.nocookie.net/gensin-impact/images/a/a7/Item_Martial_Artist%27s_Red_Flower.png/revision/latest?cb=20201009072708");
//        idToImageUrlMap.put(15,"https://www.mrguider.org/wp-content/uploads/2020/10/Maiden-Beloved-Artifact-Genshin-Impact.jpg");
//        idToImageUrlMap.put(14,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/lucky_dog-genshin-wiki-guide.jpg");
//        idToImageUrlMap.put(13,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/lavawalker-genshin-wiki-guide.jpg");
//        idToImageUrlMap.put(12,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/instructor-genshin-wiki-guide.jpg");
//        idToImageUrlMap.put(11,"https://static.wikia.nocookie.net/gensin-impact/images/6/69/Item_Snowswept_Memory.png/revision/latest?cb=20201223034535");
//        idToImageUrlMap.put(10,"https://static.wikia.nocookie.net/gensin-impact/images/9/9b/Item_Gladiator%27s_Triumphus.png/revision/latest?cb=20201120063353");
//        idToImageUrlMap.put(9,"https://lh3.googleusercontent.com/proxy/B08e9nIgBdVwYvrg2IhZECmx0KBH2luIjFVZ-2Ss2X0RQXEa8ft3ml0Qk3y8uEo4PdnTHvZQNAqzJ35Uowxi7Cqdus5Ne0UJ0qHvfj8O3EqebIfdWPyuc3fkhMJB-mo33DqQws99iwyx89cs9hTwNaRLRGDbqBede7khMuFXH5LWucA");
//        idToImageUrlMap.put(8,"https://www.gensh.in/fileadmin/Database/Artifacts/With_Background/af_gambler.jpg");
//        idToImageUrlMap.put(7,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQmPilrdbCoDfxDnFWWWpwoBevY6Ku7iyxCg&usqp=CAU");
//        idToImageUrlMap.put(6,"https://www.gensh.in/fileadmin/Database/Artifacts/With_Background/af_crimson_witch_of_flames.jpg");
//        idToImageUrlMap.put(5,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsAYI3_1bu8Ew7PIfUdGMDvxW5EUdyVUu0sg&usqp=CAU");
//        idToImageUrlMap.put(4,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuWWclfJi91jp-bxcEz6NaJwjveh_CQl0m0g&usqp=CAU");
//        idToImageUrlMap.put(3,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQJBRXlcRir6_kjTo_9h2pkQMsRTddsLeDnCQ&usqp=CAU");
//        idToImageUrlMap.put(2,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/archaic_petra-genshin-wiki-guide.jpg");
//        idToImageUrlMap.put(1,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/adventurer-genshin-wiki-guide.jpg");
//
        listView = findViewById(R.id.lvArtifacts);
//
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
//                            artifacts = new ArrayList<>();
//                            for (int i = 0; i < dataArray.length(); i++) {
//                                dbRef = FirebaseDatabase.getInstance().getReference();
//                                ArtifactsModel artifactsModel = new ArtifactsModel();
//                                JSONObject dataobj = dataArray.getJSONObject(i);
//
//                                int charId = dataobj.getInt("id");
//                                artifactsModel.setName(dataobj.getString("name"));
//                                artifactsModel.setBonus_2_set(dataobj.getString("2_set_bonus"));
//                                artifactsModel.setBonus_4_set(dataobj.getString("4_set_bonus"));
//
//                                String imageUrl = idToImageUrlMap.get(charId);
//                                artifactsModel.setImageURL(imageUrl);
//
//                                dbRef.child("Artifacts").push().setValue(artifactsModel);
//                                artifacts.add(artifactsModel);
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
//        artifactsAdapter = new ArtifactsAdapter(this, artifacts);
//        listView.setAdapter(artifactsAdapter);

        dbRef = FirebaseDatabase.getInstance().getReference();
        ArrayList<ArtifactsModel> artifactsModels = new ArrayList<>();

        dbRef.child("Artifacts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot characterSnapshot: snapshot.getChildren()){
                    ArtifactsModel value = characterSnapshot.getValue(ArtifactsModel.class);
                    artifactsModels.add(value);
                }
                artifactsAdapter = new ArtifactsAdapter(ArtifactsActivity.this, artifactsModels);
                listView.setAdapter(artifactsAdapter);
                artifactsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}