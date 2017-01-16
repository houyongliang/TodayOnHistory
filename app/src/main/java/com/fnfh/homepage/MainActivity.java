package com.fnfh.homepage;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.fnfh.R;
import com.fnfh.base.BaseFragment;
import com.fnfh.fragment.CollectPageFragment;

import com.fnfh.fragment.HomePageFragment;
import com.fnfh.fragment.OtherPageFragment;
import com.fnfh.fragment.grilpage.GirlPageFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MainActivity extends AppCompatActivity {


    @BindView(R.id.mToolBar)
    Toolbar mToolBar;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.nav)
    NavigationView nav;
    @BindView(R.id.myDrawer)
    DrawerLayout myDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager fm;
    private List<BaseFragment> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint 激活导航栏
//            tintManager.setNavigationBarTintEnabled(true);
            //设置系统栏设置颜色
            //tintManager.setTintColor(R.color.red);
            //给状态栏设置颜色
            tintManager.setStatusBarTintResource(R.color.colorAccent); /*设置上面的按钮*/
            //Apply the specified drawable or color resource to the system navigation bar.
            //给导航栏设置资源
//            tintManager.setNavigationBarTintResource(R.color.colorAccent);/*设置下面的按钮 返回*/

        }


        initData();
        initView();



    }



    private void initView() {



        mToolBar.setTitle("历史上的今天");
        mToolBar.setTitleTextColor(Color.parseColor("#ff0000"));/*设置字体颜色*/
        setSupportActionBar(mToolBar);/*设置生效*/
        //设置左上角的图标响应
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, myDrawer, mToolBar, 0, 0) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerToggle.syncState();
        myDrawer.setDrawerListener(mDrawerToggle); //设置侧滑监听
        /*设置改变 nav 里面 menu 的 图片和字体颜色*/
        Resources resource = (Resources) getBaseContext().getResources();
        ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.navigation_menu_item_color);

        nav.setItemIconTintList(null);  /*让图片就是显示他本身的颜色*/
        nav.setItemTextColor(csl);
        nav.setItemIconTintList(csl);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mToolBar.setTitle(item + "");
                switch (item.getItemId()) {
                    case R.id.item_history:
                        nav.setCheckedItem(item.getItemId());

                        showFragment(list,"HOME");
                        break;
                    case R.id.item_gril:
                        nav.setCheckedItem(item.getItemId());
                        showFragment(list,"GIRL");
//                        replaceFragment(new GirlPageFragment(), "GIRL");
                        break;
                    case R.id.item_about:
                        nav.setCheckedItem(item.getItemId());
                        showFragment(list,"OTHER");
//                        replaceFragment(new HomePageFragment(), "HOME");
                        break;
                    case R.id.item_collect:

                        nav.setCheckedItem(item.getItemId());
                        showFragment(list,"COLLECT");
//                        replaceFragment(new CollectPageFragment(), "COLLECT");
                        break;

                }
                myDrawer.closeDrawer(Gravity.LEFT);//关闭 左侧栏目
                return false;
            }
        });


    }

    private void initData() {
        fm = getSupportFragmentManager();
        list = new ArrayList<BaseFragment>();
        HomePageFragment homePageFragment = new HomePageFragment();
        GirlPageFragment girlPageFragment = new GirlPageFragment();
        CollectPageFragment collectPageFragment = new CollectPageFragment();
        OtherPageFragment otherPageFragment = new OtherPageFragment();
        list.add(homePageFragment);
        list.add(girlPageFragment);
        list.add(collectPageFragment);
        list.add(otherPageFragment);
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame,homePageFragment,"HOME");
        ft.add(R.id.frame,girlPageFragment,"GIRL");
        ft.add(R.id.frame,collectPageFragment,"COLLECT");
        ft.add(R.id.frame,otherPageFragment,"OTHER");
        ft.show(homePageFragment);
        ft.hide(girlPageFragment);
        ft.hide(collectPageFragment);
        ft.hide(otherPageFragment);
        ft.commit();


    }

//    private void replaceFragment(BaseFragment fragment, String tag) {
//        Bundle bundle = new Bundle();
//        bundle.putString("title", "首页");
//        fragment.setArguments(bundle);
//        fm.beginTransaction().replace(R.id.frame, fragment, tag).commit();
//    }

    private void setData(BaseFragment fragment,String key,String value){
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        fragment.setArguments(bundle);
    }
    private void showFragment(List<BaseFragment> list, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fg = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(tag);
        for (int i = 0; i < list.size(); i++) {
            fg.hide(list.get(i));
        }

        if (fragment == null) {
            Toast.makeText(this, "没有对象。。。,请检测 tag 是否正确", Toast.LENGTH_SHORT).show();
        } else {
            fg.show(fragment).commit();
        }
    }
}
