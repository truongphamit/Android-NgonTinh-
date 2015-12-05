package com.android.truongpq.ngontinh;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class StoryActivity extends AppCompatActivity {
    private TextView storyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        Intent intent = this.getIntent();
        String href = intent.getStringExtra(Intent.EXTRA_TEXT);
        storyTextView = (TextView) findViewById(R.id.story_textview);
        new JsoupStory(this).execute(href);

    }

    private class JsoupStory extends AsyncTask<String, Void, String> {
        private Context context;
        private ProgressDialog pd;

        public JsoupStory(Context context) {
            this.context = context;
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
            storyTextView.setText(Html.fromHtml(s));
            pd.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {
            String text = null;
            try {
                Document document = Jsoup.connect(params[0]).get();
                Elements articleContent = document.getElementsByClass("article_content");
                text = articleContent.html();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return text;
        }
    }
}
