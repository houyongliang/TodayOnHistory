package com.fnfh.Bean;

import static android.R.attr.id;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/20.
 */

public class Details {


    public String get_id() {
        return _id;
    }

    public String getD_id() {
        return d_id;
    }

    public String getTitle() {
        return title;
    }

    public String getContext() {
        return context;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }


    public Details(String _id, String d_id, String title, String context, String picUrl,String date) {
        this._id = _id;
        this.d_id = d_id;
        this.title = title;
        this.context = context;
        this.picUrl = picUrl;
        this.date=date;
    }
    public Details() {

    }

    @Override
    public String toString() {
        return "Details{" +
                "_id='" + _id + '\'' +
                ", d_id='" + d_id + '\'' +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    private String _id;
    private String d_id;
    private String title;
    private String context;
    private String picUrl;
    private String date;
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
