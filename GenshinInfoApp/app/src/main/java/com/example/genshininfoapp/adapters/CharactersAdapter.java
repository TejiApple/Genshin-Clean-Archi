package com.example.genshininfoapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genshininfoapp.R;
import com.example.genshininfoapp.models.CharactersModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CharactersAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<CharactersModel> characters;

    public CharactersAdapter (Context context, ArrayList<CharactersModel> characters){
        this.context = context;
        this.characters = characters;
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

    @SuppressLint("SetTextI18n")
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
