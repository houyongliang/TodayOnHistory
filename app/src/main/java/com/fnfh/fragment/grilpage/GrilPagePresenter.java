package com.fnfh.fragment.grilpage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fnfh.Bean.GrilData;
import com.fnfh.DetailGrilActivity;
import com.fnfh.adpter.MyGrilAdapter;
import com.fnfh.detailspages.DetaiPageActivity;
import com.fnfh.fragment.HomePageFragment;
import com.fnfh.utils.OkHttp;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

import static android.R.id.list;


/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/19.
 */

public class GrilPagePresenter implements IGrilPagePresenter {
    private static final String TAG = "GrilPagePresenter";

    private static int num = 16;
    private static int page = 1;
    GrilPageFragmentView grilPageFrgView;

    public GrilPagePresenter(GrilPageFragmentView grilPageFrgView) {
        this.grilPageFrgView = grilPageFrgView;
    }


    @Override
    public void getData(final Context context,Boolean isFirst) {
        if(isFirst){
            page=1;
        }else{
            page++;
        }
        String girlUrl = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/" + num + "/" + page;
        OkHttp.getAsync(girlUrl, new OkHttp.DataCallBack() {

            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(context, "请求数据失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                GrilData grilData = new Gson().fromJson(result, GrilData.class);
                List<GrilData.ResultsBean>  list = grilData.getResults();

                grilPageFrgView.showView(list);


            }
        });
    }

    @Override
    public void sendData(Context context,View view,List<GrilData.ResultsBean> list, int position) {
        if (list != null) {
            context.startActivity(DetailGrilActivity.getIntent(context,list.get(position).getUrl()));
        } else {
            Log.e(TAG, "sendData: " + "没有请求数据 ");
        }
    }


}
