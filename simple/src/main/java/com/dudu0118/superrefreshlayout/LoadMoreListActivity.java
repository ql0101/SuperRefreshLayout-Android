package com.dudu0118.superrefreshlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dudu0118.superrefreshlib.view.SuperRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class LoadMoreListActivity extends AppCompatActivity {
    private ListView lv;
    private List<String> mDatas;
    private SuperRefreshLayout mSuperRefreshLayout;
    public static Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadmore_listview);

        mHandler = new Handler();

        lv = (ListView) findViewById(R.id.lv_load);
        mSuperRefreshLayout = (SuperRefreshLayout) findViewById(R.id.id_superrefreshlayout);


        initDatas();
//        final MyAdapter ad = new MyAdapter(this, lv, mDatas);
        final MyAdapter2 ad = new MyAdapter2();
        lv.setAdapter(ad);
//        ad.setLoadMore(false);

        mSuperRefreshLayout.setRefreshListener(new SuperRefreshLayout.RefreshListener() {

            @Override
            public void onRefresh(final SuperRefreshLayout superRefreshLayout) {
                superRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        superRefreshLayout.finishRefresh();
                        superRefreshLayout.setLoadMore(true);

                    }
                }, 2000);
            }

            @Override
            public void onRefreshLoadMore(final SuperRefreshLayout superRefreshLayout) {
                Log.e("main","-----onRefreshLoadMore----------");
                superRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i <= 5; i++) {
                            list.add("" + i);
                        }
                        mDatas.addAll(list);
                        ad.notifyDataSetChanged();
                        superRefreshLayout.setLoadMore(false);

                        superRefreshLayout.finishLoadMore();
                    }
                },2000);

            }
        });


        mSuperRefreshLayout.autoRefresh();
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'Z'; i++) {
            mDatas.add("" + (char) i);
        }
    }


    class MyAdapter2 extends BaseAdapter {

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            TextView view = new TextView(LoadMoreListActivity.this);
            view.setTextSize(28);
            view.setTextColor(Color.BLACK);
            view.setText("item:" + getItem(position));
            return view;
        }
    }
}
