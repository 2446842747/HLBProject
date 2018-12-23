package com.example.administrator.hlbproject;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bean.Diary;
import fragment.Fragment_editor;
import fragment.Fragment_show;

public class Write_new extends AppCompatActivity {

    private EditText context;
    private List<Diary> data;
    private EditText title;
    private Diary diary;
    private int n;
    private SimpleDateFormat simpleDateFormat;
    private Date date;
    private TextView textView;
    private Spinner spinner;
    private String labelSave;
    private int num;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_new);
        Intent intent=getIntent();
        num=intent.getIntExtra("number",-1);
        Log.d("Write_new", Integer.toString(num));
         simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        //获取当前时间
         date = new Date(System.currentTimeMillis());
         textView=(TextView)findViewById(R.id.edit_date) ;
         textView.setText(simpleDateFormat.format(date));
         spinner=(Spinner)findViewById(R.id.label_choose);
        Toolbar toolbar = (Toolbar)findViewById(R.id.write_toolbar);
        setSupportActionBar(toolbar);
        //返回箭头
        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
//        spinner.setDropDownVerticalOffset(150);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                labelSave=spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                labelSave="运动";
            }
        });
        /**
         * 数据保存
         * */
        title = (EditText)findViewById(R.id.label_edit);
        context = (EditText)findViewById(R.id.context_edit);
        data=LitePal.findAll(Diary.class);
        if(num==-1){
        n=-1;
        diary = new Diary();
        for(Diary a:data)
        {
            n=a.getNum();
            Log.d("Write_new", Integer.toString(n)+"test");
        }
        n++;
        }
        else{
            n=num;
            for(Diary a:data)
            {
                Log.d("Write_new", Integer.toString(a.getNum())+"test");
                if(n==a.getNum())
                {
                    diary=a;
                    break;
                }
            }
            int i;
            for(i=0;i<Fragment_editor.label.size();i++)
            {
                if(diary.getLabel().equals(Fragment_editor.label.get(i)))break;
            }
            title.setText(diary.getTitle());
            context.setText(diary.getContent());
            spinner.setSelection(i);
        }


//        button2.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager =getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.my,new Fragment3()).commit();
//            }
//        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.determine:
                diary.setNum(n);
                diary.setTitle(title.getText().toString());
                diary.setContent(context.getText().toString());
                diary.setDate(simpleDateFormat.format(date));
                diary.setLabel(labelSave);
                diary.save();
                Log.d("Write_new", "onOptionsItemSelected: ");
                Intent intent=new Intent(Write_new.this,Passage_show.class);
                intent.putExtra("num",n);
                startActivity(intent);
                finish();
                break;
            case android.R.id.home:
                finish();
            default:
        }
        return true;
    }
}
