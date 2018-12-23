package com.example.administrator.hlbproject;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import bean.Diary;
import myadapter.DiaryAdapter;

public class SearchActivity extends AppCompatActivity {
    private DiaryAdapter diaryAdapter;
    private RecyclerView recyclerView;
    private boolean judge=false;
    private List<Diary> diaryList=new ArrayList<>();
    private SearchView searchView;
    private List<Diary> dList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        diaryList=LitePal.findAll(Diary.class);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_search);
        Toolbar toolbar = (Toolbar)findViewById(R.id.Search_toolbar);
        setSupportActionBar(toolbar);

        //recyclerView的设置
        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
        //线性，垂直或水平布局
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_search, menu);

            MenuItem searchItem = menu.findItem(R.id.search);
            //通过MenuItem得到SearchView
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setMaxWidth(1000);
            searchView.setIconified(false);//设置搜索栏打开方式
            /**
             * searchview的监听
             * */
        searchView.setQueryHint("输入要搜索的日记标题");//输入提示


        //searchview的返回按钮监听
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

@Override

public void onFocusChange(View v, boolean hasFocus) {

        if(!hasFocus)
        {
        searchView.setQuery("",true);
        hideInput();
        }
        Toast.makeText(getApplicationContext(), "hasFocus: " + hasFocus, Toast.LENGTH_SHORT).show();
        }
        });
//        completeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                searchView.setQuery(searchHistory.get(position),true);//true即为提交该字符串
//            }
//        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
@Override
public boolean onQueryTextSubmit(String query) {
        /**
         * 按下确认搜索时响应
         * */
        judge=false;
        dList.clear();
        for(Diary a:diaryList)
        {
            if(a.getTitle().equals(query))
            {
                judge=true;
                break;
            }
        }
        if(judge)
        {
            for(Diary a:diaryList) {
                if (a.getTitle().equals(query)) {
                    dList.add(a);
                }
            }
           diaryAdapter.notifyDataSetChanged();
        }
        else
        {
        Toast.makeText(SearchActivity.this,"查不到该内容",Toast.LENGTH_SHORT).show();
        }
        return false;
        }

@Override
public boolean onQueryTextChange(String newText) {
        /**
         * 搜索内容改变时响应
         * */

        return false;
        }
        });
            return super.onCreateOptionsMenu(menu);
    }

    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
//
/**
 *
 * 由于原来的searchview的Listview中的点击事件是从cursor中获取信息，用Adapter会出错
 * 所以用setOnItemClickListener方法重新设置OnItemClickListener解决这个问题
 * */
/**
 * 通过View.getResources(). getIdentifier()方法来获取View的内部控件（该方法对所有的View都适用）。
 * */
//        arrayAdapter=new ArrayAdapter<>
//                (this,android.R.layout.simple_list_item_1,searchHistory);
//        final int completeTextId = searchView.getResources().getIdentifier
//                ("android:id/search_src_text", null, null);
//        final AutoCompleteTextView completeTextView =
//                (AutoCompleteTextView)searchView.findViewById(completeTextId);
//        completeTextView.setThreshold(0);
// completeTextView.setAdapter(arrayAdapter);//搜索历史记录适配器
////下拉显示框（历史搜索）的背景颜色设置
//        completeTextView.setDropDownBackgroundResource(R.drawable.selector);


//                SearchHistory s=new SearchHistory();
//                s.setHistory(query);
//                s.save();
//                searchhis.clear();
//                searchhis = LitePal.findAll(SearchHistory.class);
//                searchHistory.clear();
//                for(SearchHistory a:searchhis)
//                {
//                    searchHistory.add(a.getHistory());
//                }
//                searchHistory.add(query);
//                for(String a:searchHistory)
//                {
//                    Log.d("MainActivity", a);
//                }
//                arrayAdapter.notifyDataSetChanged();
//                completeTextView.setVisibility(View.GONE);
//                completeTextView.setVisibility(View.VISIBLE);
