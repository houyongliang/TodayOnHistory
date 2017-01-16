package com.fnfh.adpter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fnfh.Bean.GrilData;
import com.fnfh.Bean.HistoruData;
import com.fnfh.R;

import java.util.List;

import static android.R.id.list;
import static com.fnfh.R.id.cancel_action;
import static com.fnfh.R.id.content_item;
import static com.fnfh.R.id.title_item;


/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/16.
 */

public class MyGrilAdapter extends RecyclerView.Adapter {

    //-------------------------------------------------加载更多
    private boolean isLoading;
    private static final int VIEW_ITEM = 0;
    private static final int VIEW_PROG = 1;
    private RecyclerView mRecyclerView;
    //-------------------------------------------------
    private Context context;
    private LayoutInflater inflater;
    private List<GrilData.ResultsBean> resultsBeans;

    public MyGrilAdapter(Context context, List<GrilData.ResultsBean> resultsBeans) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.resultsBeans = resultsBeans;
        //------
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == VIEW_ITEM) {
            holder = new MyViewHolder(inflater.inflate(R.layout.item_gril_rcyv, parent, false));
        } else {
            holder = new MyProgressViewHolder(inflater.inflate(R.layout.item_footer, parent, false));
        }
        return holder;
    }

    public List<GrilData.ResultsBean> getBaseDate() {
        return resultsBeans;
    }

    ;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            Glide.with(context)
                    .load(resultsBeans.get(position).getUrl())
                    .into(((MyViewHolder) holder).imgView);
        } else if (holder instanceof MyProgressViewHolder) {
            if (((MyProgressViewHolder) holder).pb != null)
                ((MyProgressViewHolder) holder).pb.setIndeterminate(true);
        }
    }


    @Override
    public int getItemCount() {
        return resultsBeans == null ? 0 : resultsBeans.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.img_gril);
        }
    }
    //----------------------------------------------------------------

    //根据不同的数据返回不同的viewType
    @Override
    public int getItemViewType(int position) {
        return resultsBeans.get(position) != null ? VIEW_ITEM : VIEW_PROG;

    }


    public class MyProgressViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar pb;

        public MyProgressViewHolder(View itemView) {
            super(itemView);
            pb = (ProgressBar) itemView.findViewById(R.id.pb);
        }

    }




    /*上拉加载*/

    //设置数据的方法  添加数据方法
    public void setDate(List<GrilData.ResultsBean> data) {

        resultsBeans = data;

        notifyDataSetChanged();
    }

    private LoadMoreDataListener mMoreDataListener;

    /*设置接口*/
    public interface LoadMoreDataListener {
        void loadMoreData();
    }

    //加载更多监听方法
    public void setOnMoreDataLoadListener(LoadMoreDataListener onMoreDataLoadListener) {
        mMoreDataListener = onMoreDataLoadListener;
    }
}


//        this.mRecyclerView=mRecyclerView;
//        if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
//            final GridLayoutManager gridLayoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();
//            //mRecyclerView添加滑动事件监听
//            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//                    int totalItemCount = gridLayoutManager.getItemCount();
//                    int lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
//                    Log.d("test", "totalItemCount =" + totalItemCount + "-----" + "lastVisibleItemPosition =" + lastVisibleItemPosition);
//                    if (!isLoading && totalItemCount <= (lastVisibleItemPosition+5 )) {
//                        //此时是刷新状态
//                        if (mMoreDataListener != null)
//                            mMoreDataListener.loadMoreData();
//                        isLoading = true;
//                    }
//                }
//            });
//        }



//-----
//    }
//    @Override
//    public MyGrilAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View inflate = inflater.inflate(R.layout.item_gril_rcyv, parent, false);
//        return new MyViewHolder(inflate);
//    }


//    @Override
//    public void onBindViewHolder(MyGrilAdapter.MyViewHolder holder, int position) {
//        Glide.with(context)
//                .load(resultsBeans.get(position).getUrl())
//                .into(holder.imgView);
//
//    }