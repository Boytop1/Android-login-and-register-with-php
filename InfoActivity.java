package com.example.hasee.friends;



import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class InfoActivity extends ActionBarActivity {
    private ListView listView;
    private ImageButton fabu;
    private ImageButton back;
    private ImageView im;
    private Bundle bundle;
    private int[] imageArray = {R.drawable.back, R.drawable.eight, R.drawable.five, R.drawable.four, R.drawable.ic_launcher,
            R.drawable.insert, R.drawable.jiahao, R.drawable.jianhao, R.drawable.one, R.drawable.seven, R.drawable.six, R.drawable.third, R.drawable.two};
    private String user;
    private String message;


        List<itemcontent> list=new ArrayList<itemcontent>();
        myAdapter contentAdepter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.info);
            im=(ImageView)findViewById(R.id.image01);
            user=getIntent().getStringExtra("user");
            if(message!=null){
            message=getIntent().getStringExtra("message");}
            fabu=(ImageButton)findViewById(R.id.fabu);
            back=(ImageButton)findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(InfoActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
            contentAdepter=new myAdapter(this,R.layout.forinfolistview,list);
            ListView listView=((ListView)findViewById(R.id.lsview));
            listView.setAdapter(contentAdepter);
            fabu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(InfoActivity.this, PubliceActivity.class);
                    intent.putExtra("user", user);//把该变量的值发送到下一个 页面
                    startActivityForResult(intent, 1);
                }
            });
        }
         //使用回传实现listview动态更新
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            String content=data.getExtras().getString("message");//接受传输过来的内容和姓名
            String name=data.getExtras().getString("user");
            int tp=data.getExtras().getInt("image");
            Log.i("cuowu",String.valueOf(tp));
            Log.i("传入的数值:", resultCode+name + content);
            if(content != ""&& name != "" && content !=null && name != null) {
                list.add(new itemcontent(tp,name, content));
            }
            contentAdepter.notifyDataSetChanged();
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
