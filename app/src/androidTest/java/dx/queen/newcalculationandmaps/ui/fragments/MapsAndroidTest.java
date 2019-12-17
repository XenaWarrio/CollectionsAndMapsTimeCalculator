package dx.queen.newcalculationandmaps.ui.fragments;

import android.widget.ProgressBar;

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
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MapsAndroidTest {

    private RecyclerView recyclerView;
    private ViewPager viewPager;
    private final int FRAGMENT_ID = 1;
    private final String TEST_RESULT = "3.0ms";
    private final String DEFAULT_RESULT = "0.0ms";


    @Rule
    public final ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            final AppInstance app = AppInstance.getInstance();
            AppInstance.getInstance().setAppComponent(DaggerAppComponent.builder().appModule(new AppModuleTest(app)).build());
        }
    };

    private void checkSingleElementInRW(int position, int stringResourceId, boolean isCalcComplete) {

        if (recyclerView == null)
            viewPager =  mainActivityActivityTestRule.getActivity().findViewById(R.id.view_pager);
            recyclerView =  viewPager.getChildAt(FRAGMENT_ID).findViewById(R.id.recycler);
        onView(allOf(withId(R.id.recycler), isDisplayed())).perform(scrollToPosition(position));
        onView(new RecyclerViewMatcher(R.id.recycler).atPosition(position))
                .check(matches(hasDescendant(withText(stringResourceId))));
        if (isCalcComplete) {
            onView(new RecyclerViewMatcher(R.id.recycler).atPosition(position))
                    .check(matches(hasDescendant(withText(TEST_RESULT))));
            onView(new RecyclerViewMatcher(R.id.recycler).atPosition(position))
                    .check(selectedDescendantsMatch(isAssignableFrom(ProgressBar.class), withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        } else {
            onView(new RecyclerViewMatcher(R.id.recycler).atPosition(position))
                    .check(matches(hasDescendant(withText(DEFAULT_RESULT))));
            onView(new RecyclerViewMatcher(R.id.recycler).atPosition(position))
                    .check(selectedDescendantsMatch(isAssignableFrom(ProgressBar.class), withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        }
    }
    private void checkAllElementsInRecyclerView(boolean isCalcComplete) {
        checkSingleElementInRW(0, R.string.add_to_treemapmap, isCalcComplete);
        checkSingleElementInRW(1, R.string.add_to_hashmap, isCalcComplete);
        checkSingleElementInRW(2, R.string.search_treemap, isCalcComplete);
        checkSingleElementInRW(3, R.string.search_hashmap, isCalcComplete);
        checkSingleElementInRW(4, R.string.remove_treemap, isCalcComplete);
        checkSingleElementInRW(5, R.string.remove_hashmap, isCalcComplete);
    }
    private void checkRecyclerViewInLoad() {
        if (recyclerView == null)
        viewPager =  mainActivityActivityTestRule.getActivity().findViewById(R.id.view_pager);
        recyclerView =  viewPager.getChildAt(FRAGMENT_ID).findViewById(R.id.recycler);
        for (int i = 0; i <= 5; i++) {
            onView(allOf(withId(R.id.recycler), isDisplayed())).perform(scrollToPosition(i));
            onView(new RecyclerViewMatcher(R.id.recycler).atPosition(i))
                    .check(selectedDescendantsMatch(isAssignableFrom(ProgressBar.class), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
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
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeRight());
        Thread.sleep(500);
        checkAllElementsInRecyclerView(false);
        testInputOnTab("100000", "6");
        Thread.sleep(500);
        checkRecyclerViewInLoad();
        Thread.sleep(4000);
        checkAllElementsInRecyclerView(true);
    }
    @Test
    public void testInputElementsEmptyError() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeRight());
        Thread.sleep(500);
        testInputOnTab("", "6");
        Thread.sleep(200);
        onView(withText(R.string.elements_empty)).inRoot(withDecorView(not( is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
    @Test
    public void testInputElementsTooLittleError() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeRight());
        Thread.sleep(500);
        testInputOnTab("0", "6");
        Thread.sleep(200);
        onView(withText(R.string.elements_zero)).inRoot(withDecorView(not( is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
    @Test
    public void testInputThreadsEmptyError() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeRight());
        Thread.sleep(500);
        testInputOnTab("100000", "");
        Thread.sleep(200);
        onView(withText(R.string.threads_empty)).inRoot(withDecorView(not( is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
    @Test
    public void testInputThreadsTooLittleError() throws InterruptedException {
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeRight());
        Thread.sleep(500);
        testInputOnTab("100000", "0");
        Thread.sleep(200);
        onView(withText(R.string.threads_zero)).inRoot(withDecorView(not( is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}

