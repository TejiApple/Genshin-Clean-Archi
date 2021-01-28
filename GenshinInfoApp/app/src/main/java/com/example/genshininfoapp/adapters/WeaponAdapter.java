package com.example.genshininfoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.genshininfoapp.R;
import com.example.genshininfoapp.models.WeaponModel;
import com.example.genshininfoapp.ui.MainActivity;
import com.example.genshininfoapp.ui.weapons.WeaponsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeaponAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<WeaponModel> weapon;

    public WeaponAdapter (Context context, ArrayList<WeaponModel> weapon){
        this.context = context;
        this.weapon = weapon;
    }
    @Override
    public int getCount() {
        return weapon.size();
    }

    @Override
    public Object getItem(int position) {
        return weapon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.item_weapons_list,null,true);

        TextView tvName, tvType, tvRarity;
        ImageView imageView;

        tvName = item.findViewById(R.id.tvWeaponName);
        tvType = item.findViewById(R.id.tvWeaponType);
        tvRarity = item.findViewById(R.id.tvWeaponRarity);

        tvName.setText(weapon.get(position).getName());
        tvType.setText(weapon.get(position).getType());
        tvRarity.setText(weapon.get(position).getRarity());

        imageView = item.findViewById(R.id.ivImage);
        Picasso.get().load(weapon.get(position).getImageURL()).into(imageView);
        return item;
    }
}
