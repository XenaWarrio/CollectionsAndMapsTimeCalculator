package dx.queen.newcalculationandmaps.ui.fragments;

import android.util.Log;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import androidx.viewpager.widget.ViewPager;
import dx.queen.newcalculationandmaps.AppInstance;
import dx.queen.newcalculationandmaps.CertainViewAction;
import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.RecyclerViewMatcher;
import dx.queen.newcalculationandmaps.model.AppModuleTest;
import dx.queen.newcalculationandmaps.model.DaggerAppComponent;
import dx.queen.newcalculationandmaps.ui.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MapsAndroidTest {

    @Rule
    public final ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            final AppInstance app = AppInstance.getInstance();
            AppInstance.getInstance().setAppComponent(DaggerAppComponent.builder().appModule(new AppModuleTest(app)).build());
        }
    };
    private final int FRAGMENT_ID = 1;
    private final String TEST_RESULT = "3.0 ms";
    private final String DEFAULT_RESULT = "0.0 ms";

    private RecyclerView recyclerView;
    private ViewPager viewPager;


    private void checkSingleElementInRW(int position, int stringResourceId, boolean isCalcComplete) {
        final String partText = mainActivityActivityTestRule.getActivity().getString(stringResourceId);

        Log.d("MAPS_TEST", "Checking " + partText);

        if (recyclerView == null) {
            viewPager = mainActivityActivityTestRule.getActivity().findViewById(R.id.view_pager);
            recyclerView = viewPager.getChildAt(FRAGMENT_ID).findViewById(R.id.recycler);
        }
        onView(allOf(withId(R.id.recycler), isDisplayed())).perform(scrollToPosition(position));

        final Matcher<View> recyclerPositionMatcher = new RecyclerViewMatcher(recyclerView).atPosition(position);
        // onView(recyclerPositionMatcher).check(matches(hasDescendant(withText(stringResourceId))));

        if (isCalcComplete) {
            onView(recyclerPositionMatcher).check(matches(hasDescendant(withText(partText + TEST_RESULT))));
//            onView(recyclerPositionMatcher)
//                    .perform(CertainViewAction.checkTextView(R.id.tv_name, partText + TEST_RESULT));
            onView(recyclerPositionMatcher)
                    .perform(CertainViewAction.checkProgressBar(R.id.progressBar, ViewMatchers.Visibility.INVISIBLE));
        } else {
            onView(recyclerPositionMatcher).check(matches(hasDescendant(withText(partText + DEFAULT_RESULT))));
//            onView(recyclerPositionMatcher)
//                    .perform(CertainViewAction.checkTextView(R.id.tv_name, partText + DEFAULT_RESULT));
            onView(recyclerPositionMatcher)
                    .perform(CertainViewAction.checkProgressBar(R.id.progressBar, ViewMatchers.Visibility.VISIBLE));
        }
    }

    private void checkAllElementsInRecyclerView(boolean isCalcComplete) {
        // TODO: FIX ORDER IN MODEL:
        // line 1: add to hash map add to tree map
        // line 2: search in hash map search in tree map etc
        checkSingleElementInRW(0, R.string.add_to_hashmap, isCalcComplete);
        checkSingleElementInRW(1, R.string.search_hashmap, isCalcComplete);
        checkSingleElementInRW(2, R.string.remove_treemap, isCalcComplete);


        checkSingleElementInRW(3, R.string.remove_hashmap, isCalcComplete);
        checkSingleElementInRW(4, R.string.add_to_treemapmap, isCalcComplete);
        checkSingleElementInRW(5, R.string.search_treemap, isCalcComplete);

    }

    private void checkRecyclerViewInLoad() {
        if (recyclerView == null) {
            viewPager = mainActivityActivityTestRule.getActivity().findViewById(R.id.view_pager);
            recyclerView = viewPager.getChildAt(FRAGMENT_ID).findViewById(R.id.recycler);
        }
        for (int i = 0; i <= 5; i++) {
            onView(allOf(withId(R.id.recycler), isDisplayed())).perform(scrollToPosition(i));
            onView(new RecyclerViewMatcher(recyclerView).atPosition(i))
                    .perform(CertainViewAction.checkProgressBar(R.id.progressBar,ViewMatchers.Visibility.VISIBLE));
        }
    }

    private void testInputOnTab(String countElements, String countThreads) {
        onView(allOf(withId(R.id.et_operations), isDisplayed())).perform(typeText(countElements));
        onView(allOf(withId(R.id.et_threads), isDisplayed())).perform(typeText(countThreads),
                closeSoftKeyboard());
        onView(allOf(withId(R.id.bt_start), isDisplayed())).perform(click());
    }

    @Test
    public void testCollections() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        checkAllElementsInRecyclerView(false);
        testInputOnTab("10", "6");
        Thread.sleep(500);
        checkRecyclerViewInLoad();
        Thread.sleep(4000);
        checkAllElementsInRecyclerView(true);
    }

    @Test
    public void testInputElementsEmptyError() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        testInputOnTab(" ", "6");
        Thread.sleep(200);
        onView(allOf(withId(R.id.et_operations), isDisplayed())).check(matches(hasErrorText(mainActivityActivityTestRule.getActivity().getString(R.string.elements_empty))));
    }

    @Test
    public void testInputElementsTooLittleError() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        testInputOnTab("0", "6");
        Thread.sleep(200);
        onView(allOf(withId(R.id.et_operations), isDisplayed())).check(matches(hasErrorText(mainActivityActivityTestRule.getActivity().getString(R.string.elements_zero))));
    }

    @Test
    public void testInputThreadsEmptyError() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        testInputOnTab("6", " ");
        Thread.sleep(200);
        onView(allOf(withId(R.id.et_threads), isDisplayed())).check(matches(hasErrorText(mainActivityActivityTestRule.getActivity().getString(R.string.threads_empty))));
    }

    @Test
    public void testInputThreadsTooLittleError() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        testInputOnTab("6", "0");
        Thread.sleep(200);
        onView(allOf(withId(R.id.et_threads), isDisplayed())).check(matches(hasErrorText(mainActivityActivityTestRule.getActivity().getString(R.string.threads_zero))));
    }
}

