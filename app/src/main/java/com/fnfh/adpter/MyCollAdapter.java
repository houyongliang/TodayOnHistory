package com.fnfh.adpter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fnfh.Bean.Details;
import com.fnfh.Bean.HistoruData;
import com.fnfh.R;
import com.fnfh.datebase.DetailBaseHelper;
import com.fnfh.helper.ItemTouchHelperAdapter;
import com.fnfh.helper.ItemTouchHelperViewHolder;
import com.fnfh.helper.OnStartDragListener;

import java.util.Collections;
import java.util.List;


/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/16.
 */

public class MyCollAdapter extends RecyclerView.Adapter<MyCollAdapter.MyViewHolder>  implements ItemTouchHelperAdapter {
private Context context;
    private  LayoutInflater inflater;
    private   List<Details> list;
    private final OnStartDragListener mDragStartListener;
    private final DetailBaseHelper helper;

    public MyCollAdapter(Context context, List<Details> list, OnStartDragListener dragStartListener){
        mDragStartListener = dragStartListener;
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.list=list;
        helper = new DetailBaseHelper(context);
    }
    @Override
    public MyCollAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.item_collect_rcyv, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyCollAdapter.MyViewHolder holder, int position) {
        setData(holder,position);
    }   

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    private void setData(MyViewHolder data,int position) {
//        position=list.size()-position;
        data.date.setText(list.get(position).getDate());
        data.title.setText(list.get(position).getTitle());
        Glide.with(context)
                .load(list.get(position).getPicUrl())
                .into( data.img);

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;

    }
    public void setNewData( List<Details> detailses){
        list=detailses;

        notifyDataSetChanged();
    }

    @Override
    public void onItemDismiss(int position) {

        helper.deltDates(list.get(position).getD_id());
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
       public  TextView date,title;
        public ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            date= (TextView) itemView.findViewById(R.id.tv_date_collect);
            title= (TextView) itemView.findViewById(R.id.tv_title_collect);
            img= (ImageView) itemView.findViewById(R.id.img_collect_collect);
        }

        @Override
        public void onItemSelected() {
            img.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            img.setBackgroundColor(0);
        }
    }
}
