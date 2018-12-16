package com.example.hasee.friends;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.IOException;


//httpUtils�ࣺhttp���ӹ�����
public class HttpLink {

    public static void httpPostMethod(String url, JSONObject json, Handler handler)
            throws IOException {
        //ʹ��httpClient���ӷ�������

        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 50000);
        HttpClient client = new DefaultHttpClient(params);

        //�ύjson���ݵ�������
        HttpPost request = new HttpPost(url);
        StringEntity se = new StringEntity(json.toString(), "UTF-8");
        se.setContentEncoding("UTF-8");
        se.setContentType("application/json");
        request.setEntity(se);
        request.setHeader("json", json.toString());
        HttpResponse response = client.execute(request);

        //��ȡ���������ص�����
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

            String res = EntityUtils.toString(response.getEntity(), "UTF-8");
            Log.d("httpResponse", res);
            Message msg = new Message();
            msg.what = 0;
            Bundle bundle = new Bundle();
            bundle.putString("res",res);
            msg.setData(bundle);
            handler.sendMessage(msg);

        }
    }
}