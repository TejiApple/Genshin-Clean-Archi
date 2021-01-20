package com.example.genshininfoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CharactersPresenter extends BaseAdapter implements CharactersContract.Presenter{

    final String url = "https://genshinlist.com/api/characters";
    private CharactersContract.View view;
    ArrayList<CharactersModel> characters;
    private CharactersPresenter asGenshinCharacterAdapter;

    private Context context;
    public CharactersPresenter (Context context, ArrayList<CharactersModel> characters){
        this.context = context;
        this.characters = characters;
    }


    @Override
    public void retrieveResult() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray dataArray = new JSONArray(response);
                            characters = new ArrayList<>();
                            for (int i = 0; i < dataArray.length(); i++) {

                                CharactersModel characterModel = new CharactersModel();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                int charId = dataobj.getInt("id");
                                characterModel.setName(dataobj.getString("name"));
                                characterModel.setDescription(dataobj.getString("description"));
                                characterModel.setGender(dataobj.getString("gender"));
                                characterModel.setBirthday(dataobj.getString("birthday"));
                                characterModel.setVision(dataobj.getString("vision"));
                                characterModel.setWeapon(dataobj.getString("weapon"));

//                                String imageUrl = idToImageUrlMap.get(charId);
//                                characterModel.setImageURL(imageUrl);
                                characters.add(characterModel);
                            }
                            setupListview();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
//                        Toast.makeText(CharactersPresenter.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    @Override
    public void setupListview() {

    }

    @Override
    public int getCount() {
        return characters.size();
    }

    @Override
    public Object getItem(int position) {
        return characters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.item_characters_list,null,true);

        TextView tvName, tvDescription, tvGender, tvBirthday, tvVision, tvWeapon;
        ImageView imageView;

        tvName = item.findViewById(R.id.tvName);
        tvDescription = item.findViewById(R.id.tvDescription);
        tvGender = item.findViewById(R.id.tvGender);
        tvBirthday = item.findViewById(R.id.tvBirthday);
        tvVision = item.findViewById(R.id.tvVision);
        tvWeapon = item.findViewById(R.id.tvWeapon);

        tvName.setText(characters.get(position).getName());
        tvDescription.setText(characters.get(position).getDescription());
        tvGender.setText(characters.get(position).getGender());
        tvBirthday.setText(characters.get(position).getBirthday());
        tvVision.setText(characters.get(position).getVision());
        tvWeapon.setText(characters.get(position).getWeapon());

        imageView = item.findViewById(R.id.ivImage);
        Picasso.get().load(characters.get(position).getImageURL()).into(imageView);
        return item;
    }
}
