package com.example.codepath.instagram;

import android.content.Context;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mkrish4 on 10/9/15.
 */
public class InstaListAdapter extends ArrayAdapter<InstaData> {
    public InstaListAdapter(Context context, List<InstaData> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        InstaData instaData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_view_layout, parent, false);
        }
        // Lookup view for data population
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        ivPhoto.setImageResource(0);
        ImageView ivAuthor = (ImageView) convertView.findViewById(R.id.ivAuthor);
        ivAuthor.setImageResource(0);
        TextView tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
        TextView tvSince = (TextView) convertView.findViewById(R.id.tvSince);
        TextView tvLike = (TextView) convertView.findViewById(R.id.tvLikeCount);
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        // Populate the data into the template view using the data object
        tvCaption.setText(Html.fromHtml("<b>" + instaData.author + "</b> -- " + instaData.caption));
        tvLike.setText(instaData.getNumLikes() + " Likes");
        tvSince.setText(instaData.getCreatedTime());
        tvAuthor.setText(instaData.author);
        Picasso.with(getContext()).load(Uri.parse(instaData.imageUrl)).into(ivPhoto);
        Picasso.with(getContext()).load(Uri.parse(instaData.authorImgUrl)).into(ivAuthor);
        /*
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(3)
                .cornerRadiusDp(30)
                .oval(false)
                .build();

        Picasso.with(getContext())
                .load(instaData.authorImgUrl)
                .fit()
                .transform(transformation)
                .into(ivAuthor);        // Return the completed view to render on screen
                */
        return convertView;
    }
}
