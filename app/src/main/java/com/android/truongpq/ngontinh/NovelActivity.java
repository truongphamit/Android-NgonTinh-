package com.android.truongpq.ngontinh;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.truongpq.ngontinh.daos.NovelDAO;
import com.android.truongpq.ngontinh.models.Novel;
import com.android.truongpq.ngontinh.networks.JsoupImage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class NovelActivity extends AppCompatActivity {
    private ImageView novelImg;
    private  TextView novelName;
    private  TextView novelAuthor;
    private  TextView novelDicription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel);

        Intent intent = this.getIntent();
        String position = intent.getStringExtra(Intent.EXTRA_TEXT);
        final Novel novel = new NovelDAO(this).findByRow(Integer.parseInt(position));

        novelImg = (ImageView) findViewById(R.id.novel_image);
        novelName = (TextView) findViewById(R.id.novel_name);
        novelAuthor = (TextView) findViewById(R.id.novel_author);
        novelDicription = (TextView) findViewById(R.id.novel_decription);
        novelDicription.setMovementMethod(new ScrollingMovementMethod());
        new JsoupImage(novel.getThumbnailUrl(), novelImg);
        novelName.setText(novel.getName());
        novelAuthor.setText(novel.getAuthor());

        new JsoupDecription(this).execute(novel.getUrl());

        Button readButton = (Button) findViewById(R.id.read_button);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), ChapListActivity.class);
                intent1.putExtra(Intent.EXTRA_TEXT, novel.getUrl());
                startActivity(intent1);
            }
        });
    }

    private class JsoupDecription extends AsyncTask<String, Void, String> {
        private ProgressDialog pd;
        private Context context;

        public JsoupDecription(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setTitle("Đang tải...");
            pd.setMessage("Vui lòng đợi.");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) novelDicription.setText(Html.fromHtml(s));
            pd.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {
            String text = null;
            try {
                Document document = Jsoup.connect(params[0]).get();
                Element decriptionElement = document.select("p").get(5);
                text = decriptionElement.html();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return text;
        }
    }
}
