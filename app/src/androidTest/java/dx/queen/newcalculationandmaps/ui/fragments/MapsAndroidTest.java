package dx.queen.newcalculationandmaps.ui.fragments;

import android.content.Context;
import android.util.Log;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dagger_stuff.AppModuleTest;
import dx.queen.newcalculationandmaps.dagger_stuff.AppInstance;
import dx.queen.newcalculationandmaps.dto.task.TaskData;
import dx.queen.newcalculationandmaps.model.DaggerAppComponent;
import dx.queen.newcalculationandmaps.model.calculator.TestCalculator;
import dx.queen.newcalculationandmaps.ui.MainActivity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MapsAndroidTest {

    CollectionsPresenter presenter;
    TestCalculator calculator;

    Context context;


    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class) {

        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            AppInstance.getInstance().setAppComponent(DaggerAppComponent.builder().appModule(new AppModuleTest(context)).build());

            calculator = (TestCalculator) DaggerAppComponent.builder().appModule(new AppModuleTest(context)).build().provideCalculator();
        }

    };

    @Before
    public void fragment(){
        onView(ViewMatchers.withId(R.id.view_pager)).perform(ViewActions.swipeRight());
        ViewInteraction mapTab = onView(withText(R.string.tab_maps));
        mapTab.perform(click());
        mapTab.check(matches(ViewMatchers.isSelected()));
    }



    @Test
    public void testAllLabelsVisible() {
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.et_operations)).check(matches(isDisplayed()));
        onView(withId(R.id.et_threads)).check(matches(isDisplayed()));
        onView(withId(R.id.bt_start)).check(matches(isDisplayed()));

    }


    @Test
    public void testWithInput() {
        onView(withId(R.id.et_operations)).perform(ViewActions.typeText("6"));
        onView(withId(R.id.et_threads)).perform(ViewActions.typeText("6"));
        onView(withId(R.id.bt_start)).perform(click());
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));


        presenter.startCalculation("6", "6");
        onView(withId(R.id.tv_name)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.progressBar),
                isDescendantOfA(allOf(withId(R.id.recycler)))))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    protected void checkProgressBarVisibility(Matcher<View> viewMatcher, int i) {
        DataInteraction progressView = onData(allOf(isDisplayed(), withId(R.id.progressBar)));
        assertNotNull(progressView);


        try {
            progressView.inAdapterView(getRecyclerMatcher())
                    .atPosition(i)
                    .check(matches(viewMatcher));
        }
        catch (Throwable e){
            Log.e("TEST", "Progress not matches " + i);
        }
    }



    public Matcher<View> getRecyclerMatcher(){
        onView(withId(R.id.recycler)).check(matches(isDisplayed()));
        Matcher<View> viewMatcher = withId(R.id.recycler);
        return viewMatcher; }


    @Test
    public void testCalculatorVisible(){
        List<TaskData> td= new ArrayList<>(6);
        for(TaskData r : td){
            calculator.execAndSetupTime(r);
        }


        onView(withId(R.id.tv_name)).check(matches(withText("3")));

    }

}
