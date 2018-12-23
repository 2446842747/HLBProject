package com.example.administrator.hlbproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import bean.Diary;
import fragment.Fragment_show;

public class Passage_show extends AppCompatActivity {

    private List<Diary> diaryList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passage_show);
        /**
         * 碎片中对toolbar的调用
         * */
        Toolbar toolbar = (Toolbar)findViewById(R.id.show_toolbar);
        setSupportActionBar(toolbar);
        //返回箭头
        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.edit_forshow:
                Intent intent=new Intent(Passage_show.this,Write_new.class);
                intent.putExtra("number",Fragment_show.num);
                startActivity(intent);
                break;
            case android.R.id.home:
                finish();
                break;
            case R.id.edit_delete:
                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                AlertDialog.Builder builder = new AlertDialog.Builder(Passage_show.this);
                //    设置Title的图标
                builder.setTitle("提示信息");
                //    设置Content来显示一个信息
                builder.setMessage("确定删除吗？");
                //    设置一个PositiveButton
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        LitePal.deleteAll(Diary.class,"num = ?",
                                Integer.toString(Fragment_show.num));
                        diaryList=LitePal.findAll(Diary.class);
                        for(Diary a:diaryList)
                        {
                            if(a.getNum()>Fragment_show.num)
                            {
                                a.setNum(a.getNum()-1);
                                a.save();
                            }
                        }

                        finish();
                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                    }
                });

                //    显示出该对话框
                builder.show();
                break;
            default:
        }
        return true;
    }
}
