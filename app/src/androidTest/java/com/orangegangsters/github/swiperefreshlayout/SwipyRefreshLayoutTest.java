package com.orangegangsters.github.swiperefreshlayout;

import android.view.View;
import android.widget.ImageView;

import com.orangegangsters.github.swipyrefreshlayout.MainActivity;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;

/**
 * Created by olivier.goutay on 4/18/16.
 */
public class SwipyRefreshLayoutTest extends AbstractTest {

    /**
     * Test that the {@link SwipyRefreshLayout#getCircleView()} is shown in the right directions.
     */
    public void testSwipyRefreshLayoutDirections() {
        //Wait for launch
        solo.waitForActivity(MainActivity.class);
        solo.assertCurrentActivity("MainActivity", MainActivity.class);
        solo.sleep(500);

        //Test TOP
        SwipyRefreshLayout swipyRefreshLayout = (SwipyRefreshLayout) solo.getView(R.id.swipyrefreshlayout);
        assertEquals(View.GONE, ((ImageView) swipyRefreshLayout.getCircleView()).getVisibility());
        clickOnView(R.id.button_top);
        drag(true);
        assertEquals(View.VISIBLE, ((ImageView) swipyRefreshLayout.getCircleView()).getVisibility());
        solo.sleep(MainActivity.DISMISS_TIMEOUT + 1000);
        assertEquals(View.GONE, ((ImageView) swipyRefreshLayout.getCircleView()).getVisibility());

        //Test BOTTOM
        clickOnView(R.id.button_bottom);
        drag(true);
        assertEquals(View.GONE, ((ImageView) swipyRefreshLayout.getCircleView()).getVisibility());
        drag(false);
        solo.sleep(500);
        drag(false);
        assertEquals(View.VISIBLE, ((ImageView) swipyRefreshLayout.getCircleView()).getVisibility());
        solo.sleep(MainActivity.DISMISS_TIMEOUT + 1000);
        assertEquals(View.GONE, ((ImageView) swipyRefreshLayout.getCircleView()).getVisibility());

        //Test BOTH
        clickOnView(R.id.button_both);
        drag(false);
        assertEquals(View.VISIBLE, ((ImageView) swipyRefreshLayout.getCircleView()).getVisibility());
        solo.sleep(MainActivity.DISMISS_TIMEOUT + 1000);
        assertEquals(View.GONE, ((ImageView) swipyRefreshLayout.getCircleView()).getVisibility());
        drag(true);
        solo.sleep(500);
        drag(true);
        assertEquals(View.VISIBLE, ((ImageView) swipyRefreshLayout.getCircleView()).getVisibility());
        solo.sleep(MainActivity.DISMISS_TIMEOUT + 1000);
        assertEquals(View.GONE, ((ImageView) swipyRefreshLayout.getCircleView()).getVisibility());
    }

}
