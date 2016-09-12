package com.dudu0118.superrefreshlayout;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 瀑布流适配器
 * 需要动态的设置View的高度
 * Created by NewOr on 2016/4/7.
 */
public class MyStaggeredAdapter extends MySimpleAdapter {

    private List<Integer> mHeights;

    public MyStaggeredAdapter(Context context, List<String> datas) {
        super(context, datas);
        mHeights = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.itemView.setLayoutParams(layoutParams);
        holder.tv.setText(mDatas.get(position));

        //回调事件调用
//        setUpItemEvent(holder);
    }
}