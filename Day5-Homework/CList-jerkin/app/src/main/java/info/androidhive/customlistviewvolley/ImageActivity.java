package info.androidhive.customlistviewvolley;

import java.io.InputStream;
        import java.net.URL;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ImageActivity extends Activity {

    ImageView mImgTrans;
    TextView mTitle;
    TextView mArtist;
    Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        String url=getIntent().getStringExtra("url");
        String title=getIntent().getStringExtra("title");
        String artist=getIntent().getStringExtra("artist");
        mImgTrans = (ImageView) findViewById(R.id.screen);
        mTitle = (TextView) findViewById(R.id.title2);
        mArtist = (TextView) findViewById(R.id.artist);
        mTitle.setText(title);
        mArtist.setText(artist);

                new LoadImage()
                        .execute(url);

    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ImageActivity.this);
            pDialog.setMessage("이미지 로딩중입니다...");
            pDialog.show();
        }

        protected Bitmap doInBackground(String... args) {
            try {
                mBitmap = BitmapFactory
                        .decodeStream((InputStream) new URL(args[0])
                                .getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return mBitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if (image != null) {
                mImgTrans.setImageBitmap(image);
                pDialog.dismiss();

            } else {
                pDialog.dismiss();
                Toast.makeText(ImageActivity.this, "이미지가 존재하지 않습니다.",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }
}

