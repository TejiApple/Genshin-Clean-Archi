package com.example.genshininfoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genshininfoapp.R;
import com.example.genshininfoapp.models.WeaponDetailsModel;
import com.example.genshininfoapp.models.WeaponModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeaponDetailsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<WeaponDetailsModel> weaponDetails;

    public WeaponDetailsAdapter (Context context, ArrayList<WeaponDetailsModel> weaponDetails){
        this.context = context;
        this.weaponDetails = weaponDetails;
    }

    @Override
    public int getCount() {
        return weaponDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return weaponDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.activity_weapon_details,null,true);

        TextView tvSkills;

        tvSkills = item.findViewById(R.id.tvSkills);

        tvSkills.setText(weaponDetails.get(position).getSkill());

        return item;
    }
}
