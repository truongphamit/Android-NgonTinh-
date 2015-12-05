package com.android.truongpq.ngontinh;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.truongpq.ngontinh.adapters.ChapsAdapter;
import com.android.truongpq.ngontinh.models.Chap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ChapListActivity extends AppCompatActivity {
    private RecyclerView rvChaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap_list);

        Intent intent = this.getIntent();
        String url = intent.getStringExtra(Intent.EXTRA_TEXT);
        rvChaps = (RecyclerView) findViewById(R.id.rvChaps);
        rvChaps.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        rvChaps.addItemDecoration(itemDecoration);

        new JsoupChapList(this).execute(url);
    }

    private class JsoupChapList extends AsyncTask<String, Void, List<Chap>> {
        private Context context;
        private ProgressDialog pd;

        public JsoupChapList(Context context) {
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
        protected void onPostExecute(List<Chap> chaps) {
            ChapsAdapter chapsAdapter = new ChapsAdapter(chaps);
            rvChaps.setAdapter(chapsAdapter);
            pd.dismiss();
        }

        @Override
        protected List<Chap> doInBackground(String... params) {
            List<Chap> chaps = new ArrayList<>();
            try {
                Document document = Jsoup.connect(params[0]).get();
                Element boxchap = document.getElementById("boxchap");
                int i = 0;
                Element a;
                do {
                    a = boxchap.select("a").get(i);
                    Chap chap = new Chap(a.text(), a.attr("href"));
                    chaps.add(chap);
                    ++i;
                } while (a != boxchap.select("a").last());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return chaps;
        }
    }
}
