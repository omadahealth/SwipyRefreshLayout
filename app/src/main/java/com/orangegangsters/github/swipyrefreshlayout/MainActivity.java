package com.orangegangsters.github.swipyrefreshlayout;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.orangegangsters.github.swiperefreshlayout.R;
import com.orangegangsters.github.swiperefreshlayout.databinding.ActivityMainBinding;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

public class MainActivity extends AppCompatActivity implements SwipyRefreshLayout.OnRefreshListener, View.OnClickListener {

    /**
     * The dismiss time for {@link SwipyRefreshLayout}
     */
    public static final int DISMISS_TIMEOUT = 2000;

    /**
     * This view binding
     */
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initLayout();
    }

    private void initLayout() {
        mBinding.listview.setAdapter(new DummyListViewAdapter(this));

        mBinding.swipyrefreshlayout.setOnRefreshListener(this);

        mBinding.buttonTop.setOnClickListener(this);
        mBinding.buttonBottom.setOnClickListener(this);
        mBinding.buttonBoth.setOnClickListener(this);
        mBinding.buttonRefresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_top:
                mBinding.swipyrefreshlayout.setDirection(SwipyRefreshLayoutDirection.TOP);
                break;
            case R.id.button_bottom:
                mBinding.swipyrefreshlayout.setDirection(SwipyRefreshLayoutDirection.BOTTOM);
                break;
            case R.id.button_both:
                mBinding.swipyrefreshlayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
                break;
            case R.id.button_refresh:
                mBinding.swipyrefreshlayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Hide the refresh after 2sec
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mBinding.swipyrefreshlayout.setRefreshing(false);
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
                        mBinding.swipyrefreshlayout.setRefreshing(false);
                    }
                });
            }
        }, DISMISS_TIMEOUT);
    }
}
