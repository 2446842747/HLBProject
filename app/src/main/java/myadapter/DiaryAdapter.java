package myadapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.hlbproject.MainActivity;
import com.example.administrator.hlbproject.Passage_show;
import com.example.administrator.hlbproject.R;
import com.example.administrator.hlbproject.Write_new;

import java.util.List;

import bean.Diary;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {
    private List<Diary> mtitle;
    //定义一个内部类ViewHolder，继承自RecyclerView.ViewHolder，用来缓存子项的各个实例，提高效率
    static class ViewHolder extends RecyclerView.ViewHolder{
        View diaryView;
        TextView title;
        TextView content;
        TextView label;

        public ViewHolder(View view){
            super(view);
            diaryView = view;
            title=(TextView)view.findViewById(R.id.diary_title);
            content=(TextView)view.findViewById(R.id.diary_content);
            label=(TextView)view.findViewById(R.id.diary_label);
        }
    }
    public DiaryAdapter(List<Diary> mtitle)
    {

        this.mtitle=mtitle;
    }
    //实现onCreateViewHolder方法，返回给recyclerView使用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_recycler,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.diaryView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Diary dia = mtitle.get(position);
                Intent intent = new Intent(v.getContext(),Passage_show.class);
                intent.putExtra("num",position);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    //实现onBindViewHolder方法，设置子Item上各个实例
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Diary diary = mtitle.get(position);
        String context=new String();
        if(diary.getContent().length()>10){
            context=diary.getContent().substring(0,9);
            context+="...";
        }else{
            context=diary.getContent();
        }
        holder.title.setText(diary.getTitle());
        holder.label.setText(diary.getLabel());
        holder.content.setText(context);
    }

    //返回子项个数
    @Override
    public int getItemCount() {
        return mtitle.size();
    }

}
