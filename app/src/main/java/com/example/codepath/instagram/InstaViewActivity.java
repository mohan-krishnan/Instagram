package com.example.codepath.instagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class InstaViewActivity extends AppCompatActivity {
    public static final String CLIENT_ID = "e7697ffb9a8e40a9a13bad4267570aac";
    public static final String URL = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;

    private List<InstaData> instaDataList = new ArrayList<>();
    private InstaListAdapter instaListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insta_view);
        instaListAdapter = new InstaListAdapter(this, instaDataList);
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(instaListAdapter);
        fetchData();
    }

    private void fetchData() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestHandle requestHandle = client.get(URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("DEBUG", response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String imageUrl = jsonArray.getJSONObject(i).getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        JSONObject captionObj = jsonArray.getJSONObject(i).optJSONObject("caption");
                        String caption = captionObj != null ? jsonArray.getJSONObject(i).getJSONObject("caption").getString("text") : "No caption";
                        String type = jsonArray.getJSONObject(i).getString("type");
                        String author = jsonArray.getJSONObject(i).getJSONObject("user").getString("username");
                        InstaData instaData = new InstaData(imageUrl, type, caption, author);
                        instaDataList.add(instaData);
                        instaData.imageHeight = jsonArray.getJSONObject(i).getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
                        instaData.numLikes = jsonArray.getJSONObject(i).getJSONObject("likes").getInt("count");
                        instaData.createdTime = jsonArray.getJSONObject(i).getLong("created_time");
                        instaData.authorImgUrl = jsonArray.getJSONObject(i).getJSONObject("user").getString("profile_picture");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                instaListAdapter.notifyDataSetChanged();
            }
        });
    }
}
