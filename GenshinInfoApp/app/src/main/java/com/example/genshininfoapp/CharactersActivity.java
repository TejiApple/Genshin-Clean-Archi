package com.example.genshininfoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class CharactersActivity extends AppCompatActivity implements CharactersContract.View{

    ListView listView;
    HashMap<Integer,String> idToImageUrlMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        initViews();
        initHashMap();
    }

    private void initViews(){
        listView = findViewById(R.id.lvCharacters);
    }

    @Override
    public void getFromAPI() {

    }

    @Override
    public void initHashMap() throws JSONException {

        idToImageUrlMap.put(6,"https://static1.srcdn.com/wordpress/wp-content/uploads/2020/10/diluc-genshin-impact-1.jpg?q=50&fit=crop&w=960&h=500");
        idToImageUrlMap.put(8,"https://www.siliconera.com/wp-content/uploads/2020/10/genshin-impact-jean-710x400.jpg");
        idToImageUrlMap.put(10,"https://img.republicworld.com/republic-prod/stories/promolarge/xxhdpi/ib8j809rqa2mz60e_1602159530.jpeg?tr=w-1242,h-710,f-jpeg");
        idToImageUrlMap.put(11,"https://www.gensh.in/fileadmin/Database/Characters/Klee/Character_Klee_XL.png");
        idToImageUrlMap.put(13,"https://cdn.mos.cms.futurecdn.net/Kf7jRtb2x4TTMDwQG4Vv8M-320-80.png");
        idToImageUrlMap.put(16,"https://assets.gamepur.com/wp-content/uploads/2020/10/12062337/best-qiqi-build-genshin-impact.jpg");
        idToImageUrlMap.put(21,"https://oyster.ignimgs.com/mediawiki/apis.ign.com/genshin-impact/4/4d/Venti.jpg");
        idToImageUrlMap.put(23,"https://static0.gamerantimages.com/wordpress/wp-content/uploads/2020/10/genshin-impact-xiao-cover.jpg");
        idToImageUrlMap.put(26,"https://www.siliconera.com/wp-content/uploads/2020/11/genshin-impact-childe-boss-fight.jpg");
        idToImageUrlMap.put(28,"https://www.siliconera.com/wp-content/uploads/2020/11/genshin-impact-zhongli-banner.jpg");
        idToImageUrlMap.put(1,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/amber-genshin-wiki-guide.png");
        idToImageUrlMap.put(2,"https://assets.gamepur.com/wp-content/uploads/2020/10/12085118/Genshin-Impact-Barbara-Best-Build.jpg");
        idToImageUrlMap.put(3,"https://www.gensh.in/fileadmin/Database/Characters/Baidou/Character_Baidou_XL.png");
        idToImageUrlMap.put(4,"https://oyster.ignimgs.com/mediawiki/apis.ign.com/genshin-impact/1/1b/Bennett.jpg");
        idToImageUrlMap.put(5,"https://www.gematsu.com/wp-content/uploads/2020/06/Genshin-Impact_2020_06-03-20_Top.jpg");
        idToImageUrlMap.put(7,"https://twinfinite.net/wp-content/uploads/2020/04/Genshin-Impact.jpg");
        idToImageUrlMap.put(9,"https://genshinimpact.wiki.fextralife.com/file/Genshin-Impact/kaeya-genshin-wiki-guide.png");
        idToImageUrlMap.put(12,"https://assets.gamepur.com/wp-content/uploads/2020/10/22064659/Lisa_best_build_Genshin_Impact.jpg");
        idToImageUrlMap.put(14,"https://www.gematsu.com/wp-content/uploads/2020/03/Genshin-Impact_2020_03-05-20_Top.jpg");
        idToImageUrlMap.put(15,"https://assets.gamepur.com/wp-content/uploads/2020/10/13085602/Noelle_best_build_Genshin_Impact.jpg");
        idToImageUrlMap.put(17,"https://www.gematsu.com/wp-content/uploads/2019/10/Genshin-Impact_2019_10-18-19_Top.jpg");
        idToImageUrlMap.put(18,"https://assets.gamepur.com/wp-content/uploads/2020/10/22053621/best_Sucrose_build_Genshin_Impact.jpg");
        idToImageUrlMap.put(22,"https://www.gematsu.com/wp-content/uploads/2020/03/Genshin-Impact_2020_03-17-20_Top.jpg");
        idToImageUrlMap.put(24,"https://imgix.bustle.com/uploads/image/2020/11/2/74798054-f09a-4c2a-848f-6df7ad33e99e-genshin-impact_2020_03-25-20_top.jpg?w=1200&h=630&fit=crop&crop=faces&fm=jpg");
        idToImageUrlMap.put(25,"https://cdn.mos.cms.futurecdn.net/ukoeRe945daYVR236Wr9Y6.jpg");
        idToImageUrlMap.put(27,"https://media.nichegamer.com/wp-content/uploads/2020/11/30151727/genshin-impact-11-30-20-3.jpg");

        JSONArray dataArray = new JSONArray();
        for (int i = 0; i < dataArray.length(); i++){
            CharactersModel characterModel = new CharactersModel();
            JSONObject dataobj = dataArray.getJSONObject(i);

            int charId = dataobj.getInt("id");

            String imageUrl = idToImageUrlMap.get(charId);
            characterModel.setImageURL(imageUrl);
        }
    }

}