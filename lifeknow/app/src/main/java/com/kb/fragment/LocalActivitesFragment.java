
package com.kb.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kb.adapter.SportOneadapter;
import com.kb.lifeknow.R;
import com.kb.model.SportModel;

import java.util.ArrayList;

public class LocalActivitesFragment extends Fragment {

    View mView;
    ListView mListview;
    ArrayList<SportModel> mList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.activity_local_fragment,null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListListener();//ListView的监听事件
    }
    public void initView(){
        mListview= (ListView) mView.findViewById(R.id.xlist_sport_activites);
        mList=new ArrayList<SportModel>();
    }

    public void setListListener(){
        for (int i=0;i<20;i++){
            SportModel model=new SportModel();
            model.setImg(R.mipmap.ic_launcher);
            model.setTitle("相约网球社"+i);
            model.setTime("时间：2016-05-22"+i);
            model.setAddr("地点：重庆大学"+i);
            model.setDescription("活动简介"+i);

            mList.add(model);
        }
        mListview.setAdapter(new SportOneadapter(getActivity(),mList));
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
