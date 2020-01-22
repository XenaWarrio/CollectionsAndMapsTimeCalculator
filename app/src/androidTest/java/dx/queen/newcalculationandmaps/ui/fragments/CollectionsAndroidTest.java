package dx.queen.newcalculationandmaps.ui.fragments;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

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
import static androidx.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4ClassRunner.class)
public class CollectionsAndroidTest {


    @Rule
    public final ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            final AppInstance app = AppInstance.getInstance();
            AppInstance.getInstance().setAppComponent(DaggerAppComponent.builder().appModule(new AppModuleTest(app)).build());
        }
    };
    private final int FRAGMENT_ID = 0;

    private final String TEST_RESULT = "3.0 ms";
    private final String DEFAULT_RESULT = "0.0 ms";

    private RecyclerView recyclerView;
    private ViewPager viewPager;


    private void checkSingleElementInRW(int position, int stringResourceId, boolean isCalcComplete) {
        final String partText = mainActivityActivityTestRule.getActivity().getString(stringResourceId)
                + (isCalcComplete ? TEST_RESULT : DEFAULT_RESULT);

        if (recyclerView == null) {
            viewPager = mainActivityActivityTestRule.getActivity().findViewById(R.id.view_pager);
            recyclerView = viewPager.getChildAt(FRAGMENT_ID).findViewById(R.id.recycler);
        }

        onView(allOf(withId(R.id.recycler), isDisplayed())).perform(scrollToPosition(position));
        Log.d("Collection_TEST", "Checking " + partText);


        final Matcher<View> recyclerPositionMatcher = new RecyclerViewMatcher(recyclerView).atPosition(position);

        onView(recyclerPositionMatcher).check(matches(hasDescendant(withText(partText))));

        if (isCalcComplete) {
            onView(recyclerPositionMatcher)
                    .check(selectedDescendantsMatch(isAssignableFrom(ProgressBar.class), withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));//VISIBLE
        } else {
            onView(recyclerPositionMatcher)
                    .check(selectedDescendantsMatch(isAssignableFrom(ProgressBar.class), withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        }
    }

    private void checkAllElementsInRecyclerView(boolean isCalcComplete) {
        checkSingleElementInRW(0, R.string.add_to_start_array_list, isCalcComplete);
        checkSingleElementInRW(1, R.string.add_to_middle_array_list, isCalcComplete);
        checkSingleElementInRW(2, R.string.add_to_end_array_list, isCalcComplete);
        checkSingleElementInRW(3, R.string.remove_start_array_list, isCalcComplete);
        checkSingleElementInRW(4, R.string.remove_middle_array_list, isCalcComplete);
        checkSingleElementInRW(5, R.string.remove_end_array_list, isCalcComplete);
        checkSingleElementInRW(6, R.string.search_array, isCalcComplete);
        checkSingleElementInRW(7, R.string.add_to_start_linked_list, isCalcComplete);
        checkSingleElementInRW(8, R.string.add_to_middle_linked_list, isCalcComplete);
        checkSingleElementInRW(9, R.string.add_to_end_linked_list, isCalcComplete);
        checkSingleElementInRW(10, R.string.remove_start_linked_list, isCalcComplete);
        checkSingleElementInRW(11, R.string.remove_middle_linked_list, isCalcComplete);
        checkSingleElementInRW(12, R.string.remove_end_linked_list, isCalcComplete);
        checkSingleElementInRW(13, R.string.search_linked, isCalcComplete);
        checkSingleElementInRW(14, R.string.add_to_start_caw, isCalcComplete);
        checkSingleElementInRW(15, R.string.add_to_middle_caw, isCalcComplete);
        checkSingleElementInRW(16, R.string.add_to_end_caw, isCalcComplete);
        checkSingleElementInRW(17, R.string.remove_start_caw, isCalcComplete);
        checkSingleElementInRW(18, R.string.remove_middle_caw, isCalcComplete);
        checkSingleElementInRW(19, R.string.remove_end_caw, isCalcComplete);
        checkSingleElementInRW(20, R.string.search_caw, isCalcComplete);
    }

    private void checkRecyclerViewInLoad() {
        if (recyclerView == null) {
            viewPager = mainActivityActivityTestRule.getActivity().findViewById(R.id.view_pager);
            recyclerView = viewPager.getChildAt(FRAGMENT_ID).findViewById(R.id.recycler);
        }
        final RecyclerViewMatcher recyclerViewMatcher = new RecyclerViewMatcher(recyclerView);
        for (int i = 0; i <= 20; i++) {
            onView(allOf(withId(R.id.recycler), isDisplayed())).perform(scrollToPosition(i));
            onView(recyclerViewMatcher.atPosition(i))
                    .check(selectedDescendantsMatch(isAssignableFrom(ProgressBar.class),
                            withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
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
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeRight());// swiperight
        Thread.sleep(200);
        checkAllElementsInRecyclerView(false);
        testInputOnTab("10", "1");
        checkRecyclerViewInLoad();
        Thread.sleep(10_00);
        checkAllElementsInRecyclerView(true);
    }


    @Test
    public void testInputElementsEmptyError() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeRight());
        Thread.sleep(500);
        testInputOnTab(" ", "6");
        Thread.sleep(200);
        onView(allOf(withId(R.id.et_operations), isDisplayed())).check(matches(hasErrorText(mainActivityActivityTestRule.getActivity().getString(R.string.elements_empty))));
    }

    @Test
    public void testInputElementsTooLittleError() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeRight());
        Thread.sleep(500);
        testInputOnTab("0", "6");
        Thread.sleep(200);
        onView(allOf(withId(R.id.et_operations), isDisplayed())).check(matches(hasErrorText(mainActivityActivityTestRule.getActivity().getString(R.string.elements_zero))));
    }

    @Test
    public void testInputThreadsEmptyError() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeRight());
        Thread.sleep(500);
        testInputOnTab("100000", " ");
        Thread.sleep(200);
        onView(allOf(withId(R.id.et_threads), isDisplayed())).check(matches(hasErrorText(mainActivityActivityTestRule.getActivity().getString(R.string.threads_empty))));
    }


    @Test
    public void testInputThreadsTooLittleError() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeRight());
        Thread.sleep(500);
        testInputOnTab("100000", "0");
        Thread.sleep(200);
        onView(allOf(withId(R.id.et_threads), isDisplayed())).check(matches(hasErrorText(mainActivityActivityTestRule.getActivity().getString(R.string.threads_zero))));
    }
}

