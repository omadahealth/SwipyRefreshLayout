package com.orangegangsters.github.swiperefreshlayout;

import android.test.ActivityInstrumentationTestCase2;
import android.view.WindowManager;

import com.orangegangsters.github.swipyrefreshlayout.MainActivity;
import com.robotium.solo.Solo;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class AbstractTest extends ActivityInstrumentationTestCase2<MainActivity> {

    protected Solo solo;

    public AbstractTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        wakeUpScreen();
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    private void wakeUpScreen() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
            }
        });
        solo.unlockScreen();
    }

    /**
     * Click on the specified view id
     *
     * @param id
     */
    protected void clickOnView(int id) {
        solo.clickOnView(solo.getView(id));
    }

    /**
     * Drags the screen to the top or down, depending on the boolean.
     */
    protected void drag(boolean top) {
        int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();

        int fromY, toY;

        // Scroll Down // Drag Up
        fromY = top ? (screenHeight / 4) : (screenHeight - (screenHeight / 4));
        toY = top ? (screenHeight - (screenHeight / 4)) : (screenHeight / 4);

        solo.drag(100, 100, fromY, toY, 10);
    }

}