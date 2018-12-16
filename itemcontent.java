package com.example.hasee.friends;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class itemcontent {
        private int db;
        private String cNickname;
        private String cContent;

        public itemcontent(int db, String c_nickname, String c_content) {
            db = db;
            cNickname = c_nickname;
            cContent = c_content;
        }

        public String getcNickname() {
            return cNickname;
        }

        public void setcNickname(String cNickname) {
            this.cNickname = cNickname;
        }


    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public String getcContent() {
            return cContent;
        }

        public void setcContent(String cContent) {
            this.cContent = cContent;
        }


    }

