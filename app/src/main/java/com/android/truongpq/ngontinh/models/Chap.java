package com.android.truongpq.ngontinh.models;

/**
 * Created by truongpq on 03/12/2015.
 */
public class Chap {
    private String name;
    private String href;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Chap(String name, String href) {

        this.name = name;
        this.href = href;
    }

    @Override
    public String toString() {
        return name;
    }
}
