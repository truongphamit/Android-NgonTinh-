package com.android.truongpq.ngontinh.networks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by truongpq on 03/12/2015.
 */
public class JsoupStory extends AsyncTask<Void, Void, String> {
    private String url;
    private Context context;
    private ProgressDialog pd;

    public JsoupStory(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setTitle("Đang tải...");
        pd.setMessage("Vui lòng đợi.");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected void onPostExecute(String s) {
        pd.dismiss();
    }

    @Override
    protected String doInBackground(Void... params) {
        String text = null;
        try {
            Document document = Jsoup.connect(url).get();
            Elements articleContent = document.getElementsByClass("article_content");
            text = articleContent.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
