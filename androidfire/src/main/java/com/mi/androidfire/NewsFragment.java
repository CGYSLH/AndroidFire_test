package com.mi.androidfire;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 暗示语 on 2016/12/6.
 */

public class NewsFragment extends Fragment {
    @BindView(R.id.rec)
    RecyclerView rec;
    private MyNewAdapter adapter;
    private ArrayList<String> list=new ArrayList<>();

    public NewsFragment getinstance(String tabs)
    {
        Log.i("tmd",tabs);
        NewsFragment newsFragment=new NewsFragment();
        Bundle bundle=new Bundle();
        bundle.putString("tabs",tabs);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsfragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initAdapter();
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        rec.setAdapter(adapter);
    }

    private void initData() {
        for (int i=0;i<30;i++) {

            list.add(getArguments().getString("tabs")+i);
        }
    }

    private void initAdapter() {
        adapter = new MyNewAdapter();
    }
   class MyNewAdapter extends RecyclerView.Adapter<MyNewAdapter.MyNewViewHolder>
   {
       @Override
       public MyNewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           return new MyNewViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.newsholder01,parent,false));
       }

       @Override
       public void onBindViewHolder(MyNewViewHolder holder, int position) {
           Log.i("tmd",list.get(position));
        holder.news01title.setText(list.get(position));
       }

       @Override
       public int getItemCount() {
           return list.size();
       }



       class MyNewViewHolder extends RecyclerView.ViewHolder
       {
            ImageView news01image;
           TextView news01title,news01content,news01data;

           public MyNewViewHolder(View itemView) {
               super(itemView);
               news01image= (ImageView) itemView.findViewById(R.id.news01_image);
               news01title= (TextView) itemView.findViewById(R.id.news01_title);
               news01content= (TextView) itemView.findViewById(R.id.news01_content);
               news01data= (TextView) itemView.findViewById(R.id.news01_data);

           }
       }
   }

}
