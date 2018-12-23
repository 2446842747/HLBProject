package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.administrator.hlbproject.R;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import bean.Diary;

public class Fragment_editor extends Fragment {

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    static public List<String> label=new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editor, container, false);
        label.clear();
        label.add("运动");
        label.add("心情");
        label.add("旅行");
        label.add("工作");
        label.add("生活");
        spinner = (Spinner)view.findViewById(R.id.label_choose);

        adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.item_label, R.id.spinner_name,label);

        spinner.setAdapter(adapter);
        return view;
    }
}

