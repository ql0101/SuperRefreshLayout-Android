package com.dudu0118.superrefreshlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dudu0118.superrefreshlib.view.SuperRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class RecelviewActivity extends AppCompatActivity {

    private SuperRefreshLayout mSuperRefreshLayout;

    private RecyclerView mRecyclerview;
    private List<String> mDatas;
    private MyStaggeredAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_listview);

        initDatas();
        initView();
        mAdapter = new MyStaggeredAdapter(this, mDatas);
        mRecyclerview.setAdapter(mAdapter);
        //设置RecyclerView的布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        mSuperRefreshLayout.setRefreshListener(new SuperRefreshLayout.RefreshListener() {
            @Override
            public void onRefresh(final SuperRefreshLayout superRefreshLayout) {
                superRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        superRefreshLayout.finishRefresh();
                    }
                },2000);

            }

            @Override
            public void onRefreshLoadMore(final SuperRefreshLayout superRefreshLayout) {
                superRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        superRefreshLayout.finishLoadMore();
                    }
                },1000);

            }
        });

        mSuperRefreshLayout.autoRefresh();
    }

    private void initView() {
        mRecyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);
        mSuperRefreshLayout = (SuperRefreshLayout) findViewById(R.id.id_superrefreshlayout);
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'y'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private class MySimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private Context mContext;
        protected List<String> mDatas;
        private LayoutInflater mInflater;

        public MySimpleAdapter(Context context, List<String> mDatas) {
            this.mContext = mContext;
            this.mDatas = mDatas;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.item_single_textview, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position, List<Object> payloads) {
            holder.tv.setText(mDatas.get(position));
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_tv);
        }

    }
}
