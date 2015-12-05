package com.android.truongpq.ngontinh.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by truongpq on 29/11/2015.
 */
public class Novel {
    private String name;
    private String url;
    private String thumbnailUrl;
    private String author;
    private String decription;

    public Novel() {

    }

    public Novel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Novel(String name, String url, String thumbnailUrl, String author) {
        this.name = name;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return name + " - " + url;
    }

    public static List<Novel> createNovelList(int num) {
        List<Novel> novels = new ArrayList<Novel>();

        for (int i = 0; i < num; i++) {
            novels.add(new Novel("Novel " + i, "http;//xxx"));
        }

        return novels;
    }
}
