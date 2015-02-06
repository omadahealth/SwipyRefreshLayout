package com.orangegangsters.github.swipyrefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.orangegangsters.github.swiperefreshlayout.R;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

public class MainActivity extends ActionBarActivity implements SwipyRefreshLayout.OnRefreshListener, View.OnClickListener {

    private ListView mListView;
    private SwipyRefreshLayout mSwipyRefreshLayout;
    private Button mTop;
    private Button mBottom;
    private Button mBoth;
    private Button mButtonRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }

    private void initLayout() {
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(new DummyListViewAdapter(this));

        mSwipyRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        mSwipyRefreshLayout.setOnRefreshListener(this);

        mTop = (Button) findViewById(R.id.button_top);
        mBottom = (Button) findViewById(R.id.button_bottom);
        mBoth = (Button) findViewById(R.id.button_both);
        mButtonRefresh = (Button) findViewById(R.id.button_refresh);

        mTop.setOnClickListener(this);
        mBottom.setOnClickListener(this);
        mBoth.setOnClickListener(this);
        mButtonRefresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_top:
                mSwipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.TOP);
                break;
            case R.id.button_bottom:
                mSwipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTTOM);
                break;
            case R.id.button_both:
                mSwipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
                break;
            case R.id.button_refresh:
                mSwipyRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Hide the refresh after 2sec
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mSwipyRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }, 2000);
                break;
        }
    }

    /**
     * Called when the {@link com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout}
     * is in refresh mode. Just for example purpose.
     */
    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        Log.d("MainActivity", "Refresh triggered at "
                + (direction == SwipyRefreshLayoutDirection.TOP ? "top" : "bottom"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Hide the refresh after 2sec
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSwipyRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }, 2000);
    }
}
