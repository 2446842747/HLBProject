package com.example.administrator.hlbproject;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import bean.Diary;
import bean.SearchHistory;
import fragment.Fragment_editor;
import myadapter.DiaryAdapter;

public class MainActivity extends AppCompatActivity {

    private android.support.design.widget.FloatingActionButton writeFab ;
    private android.support.design.widget.FloatingActionButton calendarFab;
    private List<SearchHistory> searchhis=new ArrayList<SearchHistory>();
    private List<String> searchHistory=new ArrayList<String>();
    private ArrayAdapter arrayAdapter ;
    private List<Diary> diaryList=new ArrayList<>();
    private List<Diary> dList=new ArrayList<>();
    private DiaryAdapter diaryAdapter;
    private RecyclerView recyclerView;
    private boolean quit =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *设置toolbar
         * */
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
      /**
       *receclerview的设置
       * */
        diaryList=LitePal.findAll(Diary.class);
        for(Diary d:diaryList)
        {
            dList.add(d);
        }
         recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this); //线性，垂直或水平布局
        //设置为水平排列，用setOrientation方法设置
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        diaryAdapter = new DiaryAdapter(dList);
        //设置recyclerView的布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置recyclerView的适配器
        recyclerView.setAdapter(diaryAdapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom=8;
            }
        });

       /**
        * recyclerviewd的设置
        * */
        LitePal.getDatabase();/**  数据库的创建 */
        searchhis = LitePal.findAll(SearchHistory.class);
//        LitePal.deleteAll(SearchHistory.class);
        for(SearchHistory a:searchhis)
        {
            searchHistory.add(a.getHistory());
        }
        /**
         * 适配器的创建*/


        final FloatingActionMenu fab = (FloatingActionMenu) findViewById(R.id.fab);
        writeFab = (android.support.design.widget.FloatingActionButton)findViewById(R.id.write_fab);
        calendarFab = (android.support.design.widget.FloatingActionButton)findViewById(R.id.calendar_fab);
        /**
         * 悬浮按钮的点击事件与实例化
         * */
        writeFab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,Write_new.class);
                Intent intent = new Intent(MainActivity.this,Write_new.class);
                startActivity(intent);
            }
        });
        calendarFab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Calendar.class);
                startActivity(intent);
            }
        });
       // fab.setClosedOnTouchOutside(false);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        dList.clear();
        diaryList=LitePal.findAll(Diary.class);
        for(Diary d:diaryList)
        {
            dList.add(d);
        }
        diaryAdapter.notifyDataSetChanged();
        super.onStart();
    }


    @Override
    public void onBackPressed() {
        if (quit == false) {    //询问退出程序
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Timer(true).schedule(new TimerTask() {   //启动定时任务
                @Override
                public void run() {
                    quit = false;  //重置退出标识
                }
            }, 2000);        //2秒后运行run()方法
            quit = true;
        } else {          //确认退出程序
            super.onBackPressed();
            finish();
        }
    }


    /**
     * toolbar上的按钮点击事件
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.setting:
                break;
            case R.id.search_view:
                //Toast.makeText(MainActivity.this,"dianji",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
                break;
            default:
        }
        return true;
    }


}
