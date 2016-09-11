package com.dudu0118.superrefreshlib.holder;


import android.content.Context;
import android.view.View;

import com.dudu0118.superrefreshlib.R;


public class LoadMoreHolder extends BaseHolder<Integer> {
    public static final int STATE_LOADING = 0;    // 加载中的状态
    public static final int STATE_ERROR = 1;    // 加载失败的状态
    public static final int STATE_EMPTY = 2;    // 没有更多数据的状态

    private View mErrorView;

    private View mLoadingView;
    private int mCurrentSate;

    public LoadMoreHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.item_load_more, null);

        refreshUI(STATE_LOADING);

        return view;
    }

    @Override
    protected void refreshUI(Integer data) {
        this.mCurrentSate = data;

        switch (data) {
            case STATE_EMPTY:
//                mErrorView.setVisibility(View.GONE);
//                mLoadingView.setVisibility(View.GONE);
                break;
            case STATE_ERROR:
//                mErrorView.setVisibility(View.VISIBLE);
//                mLoadingView.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
//                mErrorView.setVisibility(View.GONE);
//                mLoadingView.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public int getCurrentState() {
        return mCurrentSate;
    }

}
