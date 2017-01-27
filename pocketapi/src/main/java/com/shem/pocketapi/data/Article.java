package com.shem.pocketapi.data;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by smagnezi on 19/01/2017.
 */


public class Article {
    public String item_id;
    public String resolved_id;
    public String given_url;
    public String resolved_url;
    public String given_title;
    public String resolved_title;
    public String favorite;
    public String status;
    public String excerpt;
    public String is_article;
    public String has_image;
    public String has_video;
    public String word_count;
    public JSONObject tags;
    public JSONObject authors;
    public JSONObject images;
    public JSONObject videos;
}
