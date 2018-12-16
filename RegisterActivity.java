package com.example.hasee.friends;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity{

    JSONObject js=new JSONObject();
    private Handler handler;
    private String url = "http://192.168.43.244/resgiter.php";
    public static void postJson(String address, okhttp3.Callback callback, String jsonStr)
    {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, jsonStr);
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resgiter);

        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password1 = (EditText)findViewById(R.id.password1);
        final EditText password2 = (EditText)findViewById(R.id.password2);
        Button zhuce = (Button)findViewById(R.id.zhuce);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                if (msg.what==123)
                {
                    Intent intent = new Intent(RegisterActivity.this, InfoActivity.class);
                    startActivity(intent);
                }
                else if (msg.what == 234)
                {
                    Toast.makeText(RegisterActivity.this, "×¢²áÊ§°Ü",Toast.LENGTH_SHORT).show();
                }

            }
        };

        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            js.put("UserName", username.getText().toString().trim());
                            js.put("PassWord1", password1.getText().toString().trim());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String jsonStr = js.toString();
                        postJson(url, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e("TAG", "NetConnect Error!");
                            }
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String responseBodyStr = response.body().string();
                                try {
                                    JSONObject jsonData = new JSONObject(responseBodyStr);
                                    int success = Integer.parseInt(jsonData.getString("success"));
                                    Message msg = handler.obtainMessage();
                                    if (success == 1)
                                    {
                                        msg.what = 123;
                                        handler.sendMessage(msg);
                                    } else
                                    {
                                        msg.what = 234;
                                        handler.sendMessage(msg);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, jsonStr);
                    }
                }).start();
            }
        });
    }


}

