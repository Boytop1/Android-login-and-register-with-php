package com.example.hasee.friends;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;


public class LoginActivity extends ActionBarActivity {

    String url="http://192.168.123.2/login.php";
    Handler handler;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                super.handleMessage(msg);

                if(msg.what==0) {

                    try {
                        //json去BOM头的用法,解决json格式异常
                        //String jsonStr;
                        // if(jsonStr != null && jsonStr.startsWith("\ufeff"))
                        // { jsonStr = jsonStr.substring(1); }
                        // JSONObject json = new JSONObject(jsonStr);
                       String resp = msg.getData().getString("res");

                        JSONObject result = new JSONObject(resp);

                        int success = Integer.parseInt(result.getString("success"));
                      //  Toast.makeText(LoginActivity.this, res + ":\n" +result.toString(), Toast.LENGTH_LONG).show();
                        if(success == 0){
                            Intent intent = new Intent(LoginActivity.this, InfoActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("user",username.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "输入的用户名或密码有错", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                }
            }
        };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Button login=(Button)findViewById(R.id.denglu);
        Button register=(Button)findViewById(R.id.zhuce);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {

                    @Override

                    public void run() {

                        super.run();
                        try {

                            JSONObject json = new JSONObject();

                            json.put("UserName", username.getText().toString().trim());

                            json.put("PassWord", password.getText().toString().trim());

                            HttpLink.httpPostMethod(url, json, handler);


                        } catch (JSONException e) {

                            Log.d("json", "解析JSON出错");

                            e.printStackTrace();

                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("user",username.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

            @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
