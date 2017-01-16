package com.fnfh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;


import com.fnfh.homepage.MainActivity;
import com.fnfh.utils.DateUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, OnMonthChangedListener, OnDateSelectedListener {

    @BindView(R.id.calendar_toolbar)
    Toolbar calendarToolbar;
    @BindView(R.id.calendarView)
    MaterialCalendarView calendarView;
    @BindView(R.id.calendarOk)
    Button calendarOk;
    private static String showDate = "";
    public static boolean isSend = false;
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);/*初始化数据*/
        isSend = false;
        /*初始化控件*/
        initView();


    }

    private void initView() {
        // 显示兴起补全的整个礼拜的上个月或者下个月的日期 一般会多出一行整个礼拜
        // 点击补全出来的另外一个月的信息 可以直接跳到那个月
        calendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);

        // 设置日历默认的时间为当前的时间
        calendarView.setSelectedDate(new Date());

        // 日历的主要设置
        calendarView.state().edit()
                // 设置你的日历 第一天是周一还是周一
                .setFirstDayOfWeek(Calendar.MONDAY)
                // 设置你的日历的最小的月份  月份为你设置的最小月份的下个月 比如 你设置最小为1月 其实你只能滑到2月
                .setMinimumDate(CalendarDay.from(2015, 1, 1))
                // 同最小 设置最大
                .setMaximumDate(CalendarDay.from(2018, 1, 1))
                // 设置你的日历的模式  是按月 还是按周
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

// 设置你选中日期的背景底色
        //       mcv.setSelectionColor(getResources().getColor(R.color.date_choose));
        calendarView.setSelectionColor(0xff4285f4);




        calendarOk.setOnClickListener(this);/*设置 button 的点击事件*/
        /*设置 toolbar 事件*/
        calendarToolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(calendarToolbar);

        //设置监听
        calendarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(CalendarActivity.this, "setNavigationOnClickListener", Toast.LENGTH_SHORT).show();
            }
        });
        /*设置  calendarView 监听*/
        //设置点击选择日期改变事件
        calendarView.setOnDateChangedListener(this);
        //设置滑动选择改变月份事件
        calendarView.setOnMonthChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calendarOk:
                isSend = true;

                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                if(TextUtils.isEmpty(showDate)){

                    showDate= DateUtil.getNowDateStr();
                    Toast.makeText(this,showDate, Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this,showDate, Toast.LENGTH_SHORT).show();
                intent.putExtra("showDate", showDate);

                startActivity(intent);
                finish();
//                Toast.makeText(this,FORMATTER.format(date.getDate()), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        if (date == null) {

            showDate = FORMATTER.format(new Date(System.currentTimeMillis()));
            Toast.makeText(this,showDate, Toast.LENGTH_SHORT).show();
        } else {
//            int i = date.getCalendar().get(Calendar.DAY_OF_WEEK);
//            int i1 = date.getCalendar().get(Calendar.DAY_OF_MONTH);
//            int i2 = date.getCalendar().get(Calendar.DAY_OF_YEAR);
//            showDate=(i2+1900)+"年"+(i+1)+"月"+i1+"日";
            showDate = (FORMATTER.format(date.getDate()));
//            Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
//            intent.putExtra("showDate",showDate);
//            startActivity(intent);
//            finish();
            Toast.makeText(this,showDate, Toast.LENGTH_SHORT).show();
        }
    }
}
