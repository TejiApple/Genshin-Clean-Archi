package com.example.genshininfoapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CharactersPresenter extends BaseAdapter implements CharactersContract.Presenter{

    final String url = "https://genshinlist.com/api/characters";
    private CharactersContract.View view;
    ArrayList<CharactersModel> characters;
    private CharactersPresenter asGenshinCharacterAdapter;

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
                        Toast.makeText(CharactersPresenter.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
