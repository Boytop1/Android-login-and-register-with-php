package com.example.hasee.friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class myAdapter extends ArrayAdapter {

    private int resourceId;
    private int[] imageArray = {R.drawable.back, R.drawable.eight, R.drawable.five, R.drawable.four, R.drawable.ic_launcher,
            R.drawable.insert, R.drawable.jiahao, R.drawable.jianhao, R.drawable.one, R.drawable.seven, R.drawable.six, R.drawable.third, R.drawable.two};

    public myAdapter(Context context, int resource,List list) {
        super(context,resource,list);
        this.resourceId=resource;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        itemcontent content= (itemcontent) getItem(position);
        ListLayout listLayout=new ListLayout();
        View view;
        if(convertView == null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            listLayout.image = (ImageView) view.findViewById(R.id.image01);
            listLayout.textView1 = (TextView) view.findViewById(R.id.textView1);
            listLayout.textView3 = (TextView) view.findViewById(R.id.textView3);
            view.setTag(listLayout);
        }else{
            view=convertView;
            listLayout = (ListLayout) view.getTag();
        }
        Random random=new Random();
        int i=random.nextInt(13);
        listLayout.image.getLayoutParams().height=150;
        listLayout.image.getLayoutParams().width=150;
        listLayout.image.setImageResource(imageArray[i]);
        listLayout.textView1.setText(content.getcNickname());
        listLayout.textView3.setText(content.getcContent());
        return view;
    }

    class ListLayout{
        private TextView textView1;
        private TextView textView3;
        private ImageView image;
    }

}