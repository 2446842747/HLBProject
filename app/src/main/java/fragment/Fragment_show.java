package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.hlbproject.MoreTextView;
import com.example.administrator.hlbproject.R;
import com.example.administrator.hlbproject.Write_new;

import org.litepal.LitePal;

import java.util.List;

import bean.Diary;

public class Fragment_show extends Fragment {

    private List<Diary> data;
    private Diary d;
    private TextView label_show;
    private TextView title_show;
    private TextView date_show;
    static public int num;
    private int n;
    private MoreTextView moreTextView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.framgment_show,container,false);
        moreTextView = (MoreTextView) view.findViewById(R.id.expandable_textShow);
        label_show=(TextView)view.findViewById(R.id.label_show);
        title_show = (TextView)view.findViewById(R.id.title_show);
        date_show = (TextView)view.findViewById(R.id.date_show);
        Intent intent=getActivity().getIntent();
        n=intent.getIntExtra("num",-1);
        num=n;
        data=LitePal.findAll(Diary.class);
        for(Diary a:data)
        {
            if(a.getNum()==n){
                d=a;
                break;
            }
        }
        Log.d("Passage_show", d.getTitle()+"1");
        date_show.setText(d.getDate());
        title_show.setText(d.getTitle());
        label_show.setText(d.getLabel());
        moreTextView.setText(d.getContent());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        data=LitePal.findAll(Diary.class);
        for(Diary a:data)
        {
            if(a.getNum()==n){
                d=a;
                break;
            }
        }
        Log.d("Passage_show", d.getTitle()+"1");
        date_show.setText(d.getDate());
        title_show.setText(d.getTitle());
        label_show.setText(d.getLabel());
        moreTextView.setText(d.getContent());
    }
}
