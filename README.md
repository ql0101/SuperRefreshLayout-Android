# SuperRefreshLayout-Android

[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)

## 简介
这是一个集合了下拉刷新，上拉加载更多的控件，使用起来超级简单。支持最小API9 以上。

目前实现了3种下拉刷新样式与1种上拉加载样式。


## 效果图
![SuperRefreshLayout1](https://raw.githubusercontent.com/ql0101/SuperRefreshLayout-Android/master/gif/simple.gif)
![SuperRefreshLayout2](https://raw.githubusercontent.com/ql0101/SuperRefreshLayout-Android/master/gif/gooview.gif)
![SuperRefreshLayout3](https://raw.githubusercontent.com/ql0101/SuperRefreshLayout-Android/master/gif/hahahah.gif)

##快速使用
###1.在布局文件中添加SuperRefreshLayout

```xml
<com.dudu0118.superrefreshlib.view.SuperRefreshLayout
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/id_superrefreshlayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:show_type="material"
    app:overlay="true"
    app:pull_endless="true">

    <!-- 需要刷新的控件 -->
    <android.support.v7.widget.RecyclerView
        android:id="@+id/id_recyclerview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fadingEdge="none"/>

</com.dudu0118.superrefreshlib.view.SuperRefreshLayout>
```
  
###2.在代码中设置监听回调

```java
SuperRefreshLayout.setRefreshListener(new SuperRefreshLayout.RefreshListener() {
     @Override
    public void onRefresh(final SuperRefreshLayout superRefreshLayout) {
        //这里写下拉刷新的代码...

        superRefreshLayout.finishRefresh();
    }

    @Override
    public void onRefreshLoadMore(final SuperRefreshLayout superRefreshLayout) {
        //这里写上拉加载的的代码...  

        superRefreshLayout.finishLoadMore();
               
        }
    });
```
  
就需要上述的两步，就能实现下拉刷新与上拉加载 是不是很简单！

##个性化配置
###1.下拉沉浸式模式
在xml中配置：

```xml
<com.dudu0118.superrefreshlib.view.SuperRefreshLayout
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:overlay="true">
```

在代码中配置：

```java
SuperRefreshLayout.setIsOverLay(true);
```

###2.下拉无限拉模式
在xml中配置：

```xml
<com.dudu0118.superrefreshlib.view.SuperRefreshLayout
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:pull_endless="true">
```

在代码中配置：

```java
SuperRefreshLayout.setIsOverLay(true);
```

###3.下拉样式选择

在xml中配置：

```xml
<com.dudu0118.superrefreshlib.view.SuperRefreshLayout
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    <!-- 目前支持3种样式defstr文字-material官方-goo粘性 -->
    app:show_type="defstr">
```

在代码中配置：

```java
SuperRefreshLayout.setHeadView(new TextHeadView(context));
```

##高级进阶
###1.实现自定义下拉刷新头

需要创建一个class继承DefaultHeadView

实现initView()与setPullState(int statePullRefresh)效果。前者是具体显示的刷新头部view后者是用来判断当前头部状态来分别显示不同的效果。

```java
public class GooHeadView extends DefaultHeadView {
    private GooView gooView;
	
    public GooHeadView(Context context) {
        super(context);
	}
	
    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.item_gooview_refresh, null);
        gooView = (GooView) view.findViewById(R.id.gv_head);
        return view;
    }
	
    @Override
    protected void setPullState(int statePullRefresh) {
        switch (statePullRefresh) {
            case SuperRefreshLayout.STATE_PULL_REFRESH:
                gooView.removeCallbak();
	            break;
	        case SuperRefreshLayout.STATE_REFRESHING:
	            gooView.refresh();
	            break;
	    }
	
	}
}
```

如果你需要知道当前下拉的百分比来实现更丰富的效果那么请实现updatePullOffset(float offset)方法。offset为当前下拉百分比，当使用无尽模式时百分比会大于1.0

##联系作者

###**e-maill：**ql0571@qq.com

###**QQ：**941189


####如果觉得好请大家给个星星，你的评价是给作者最大的肯定。

####使用中如果发现什么bug也可以加QQ或发邮件联系作者。



