package com.android.truongpq.ngontinh.networks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by truongpq on 01/12/2015.
 */
public class JsoupImage{
    private String url;
    private ImageView imageView;

    public JsoupImage(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;

        new LoadImage().execute(url);
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        protected Bitmap doInBackground(String... args) {
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                imageView.setImageBitmap(image);
            }
        }
    }
}
