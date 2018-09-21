package com.groupe36.supcom.guidalz_10;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by safouene on 12/11/2017.
 */

public class Rdv_cellAdapter extends ArrayAdapter<Rdv> {

    //tweets est la liste des models à afficher
    public Rdv_cellAdapter(Context context, List<Rdv> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rdv_cell,parent, false);
        }

        RdvViewHolder viewHolder = (RdvViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new RdvViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.hour = (TextView) convertView.findViewById(R.id.heur);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Rdv tweet = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.pseudo.setText(tweet.getLocation());
        viewHolder.date.setText(tweet.getDate());
        viewHolder.hour.setText(tweet.getHour());

        return convertView;
    }

    private class RdvViewHolder{
        public TextView pseudo;
        public TextView hour;
        public TextView date;
    }
}
