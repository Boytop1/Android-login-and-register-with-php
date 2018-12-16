package com.example.hasee.friends;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

public class PubliceActivity extends ActionBarActivity {

    private String user;
    private ImageView image;
    private ImageButton back;
    private Button upload;
    int tp;
    String zimage;
    private Bitmap bitmap;
    private Bitmap bitmap1;
    private int[] imageArray = {R.drawable.back, R.drawable.eight, R.drawable.five, R.drawable.four, R.drawable.ic_launcher,
            R.drawable.insert, R.drawable.jiahao, R.drawable.jianhao, R.drawable.one, R.drawable.seven, R.drawable.six, R.drawable.third, R.drawable.two};
    int randomImage = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fabu);
        user = getIntent().getStringExtra("user");
        image = (ImageView) findViewById(R.id.image);
        back = (ImageButton) findViewById(R.id.backto);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();

                randomImage = random.nextInt(13);

                tp = imageArray[randomImage];
                image.setImageResource(tp);
                bitmap = BitmapFactory.decodeResource(getResources(), tp);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PubliceActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
        upload = (Button) findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sc = ((EditText) findViewById(R.id.message)).getText().toString();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("message", sc);
                bundle.putString("user", user);
                bundle.putInt("image", randomImage);
                intent.putExtras(bundle);
                PubliceActivity.this.setResult(1, intent);
                PubliceActivity.this.finish();

            }
        });
    }
}