package com.fnfh.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fnfh.Bean.HistoruData;
import com.fnfh.R;

import java.util.List;
import java.util.zip.Inflater;

import static android.R.attr.data;
import static android.R.id.list;


/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private  LayoutInflater inflater;
    private   List<HistoruData.ResultBean> list;
    public MyAdapter(Context context, List<HistoruData.ResultBean> list){
        inflater = LayoutInflater.from(context);
        this.list=list;
    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.item_rcyv, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        setData(holder,position);
    }   

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(MyViewHolder data,int position) {
//        position=list.size()-position;
        data.title_item.setText(list.get(position).getDate());
        data.content_item.setText(list.get(position).getTitle());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       public  TextView title_item,content_item;
        public MyViewHolder(View itemView) {
            super(itemView);
            title_item= (TextView) itemView.findViewById(R.id.title_item);
            content_item= (TextView) itemView.findViewById(R.id.content_item);
        }
    }
}
