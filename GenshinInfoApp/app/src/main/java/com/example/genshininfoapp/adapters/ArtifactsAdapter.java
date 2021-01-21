package com.example.genshininfoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genshininfoapp.R;
import com.example.genshininfoapp.models.ArtifactsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArtifactsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ArtifactsModel> artifacts;

    public ArtifactsAdapter (Context context, ArrayList<ArtifactsModel> artifacts){
        this.context = context;
        this.artifacts = artifacts;
    }

    @Override
    public int getCount() {
        return artifacts.size();
    }

    @Override
    public Object getItem(int position) {
        return artifacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.item_artifacts_list,null,true);

        TextView tvName, tvBonus2set, tvBonus4set;
        ImageView imageView;

        tvName = item.findViewById(R.id.tvArtifactName);
        tvBonus2set = item.findViewById(R.id.tvArtifactBonus2Set);
        tvBonus4set = item.findViewById(R.id.tvArtifactBonus4Set);


        tvName.setText(artifacts.get(position).getName());
        tvBonus2set.setText(artifacts.get(position).getBonus_2_set());
        tvBonus4set.setText(artifacts.get(position).getBonus_4_set());

        imageView = item.findViewById(R.id.ivImage);
        Picasso.get().load(artifacts.get(position).getImageURL()).into(imageView);
        return item;
    }
}
