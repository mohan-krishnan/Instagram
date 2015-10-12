package com.example.codepath.instagram;

import android.text.format.DateUtils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by mkrish4 on 10/9/15.
 */
public class InstaData {
    public String imageUrl;
    public String imageType;
    public String caption;
    public String author;
    public int imageHeight;
    public int numLikes;
    public long createdTime;
    public String authorImgUrl;

    public InstaData(String imageUrl, String imageType, String caption, String author) {
        this.imageUrl = imageUrl;
        this.imageType = imageType;
        this.caption = caption;
        this.author = author;
    }

    public String getCreatedTime() {
        return String.valueOf(DateUtils.getRelativeTimeSpanString(createdTime * 1000));
    }

    public String getNumLikes() {
        return NumberFormat.getIntegerInstance(Locale.US).format(numLikes);
    }
}
