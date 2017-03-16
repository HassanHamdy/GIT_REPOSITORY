package com.example.owner.github_repos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Owner on 16-Mar-17.
 */

public class adapter extends ArrayAdapter {

    private Context context;
    private ArrayList<RepoDetails> details;

    public adapter(Context cont, ArrayList<RepoDetails> data) {
        super(cont, 0, data);

        details = data;
        context = cont;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolderItem viewHolder;
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.details_view, parent, false);
            viewHolder = new viewHolderItem();
            viewHolder.image = (ImageView) rowView.findViewById(R.id.avatarImg);
            viewHolder.name = (TextView) rowView.findViewById(R.id.RepoName);
            viewHolder.userName = (TextView) rowView.findViewById(R.id.usrname);
            viewHolder.description = (TextView) rowView.findViewById(R.id.description);
        } else {
            viewHolder = (viewHolderItem) rowView.getTag();
        }

        Picasso.with(context).load(details.get(position).getAvatarUrl()).into(viewHolder.image);
        viewHolder.name.setText(details.get(position).getName());
        viewHolder.userName.setText("(" + details.get(position).getUsername() + ")");
        viewHolder.description.setText(details.get(position).getDesc());
        rowView.setTag(viewHolder);
        return rowView;

    }

    private class viewHolderItem {
        ImageView image;
        TextView name, userName, description;
    }
}
