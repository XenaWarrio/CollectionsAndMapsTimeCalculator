package dx.queen.newcalculationandmaps;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import dx.queen.newcalculationandmaps.dto.Modes;
import dx.queen.newcalculationandmaps.model.DaggerAppComponent;
import dx.queen.newcalculationandmaps.ui.MainActivity;
import dx.queen.newcalculationandmaps.ui.fragments.CollectionAdapter;
import dx.queen.newcalculationandmaps.ui.fragments.CollectionsFragment;
import dx.queen.newcalculationandmaps.ui.fragments.CollectionsPresenter;
import dx.queen.newcalculationandmaps.ui.fragments.DaggerFragmentComponent;
import dx.queen.newcalculationandmaps.ui.fragments.FragmentModule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4ClassRunner.class)
public class CollectionsAndroidTest {

    CollectionsFragment fragment;
    CollectionsPresenter presenter;

    @Inject
    TestCalculator calculator;

//...
//Проверить результат расчета (здесь тебе нужно содать тестовый калькулятор,
// который всегда будет возвращать одно и тоже время, например 3мс,
// чтобы можно было проверить его в ui и заинжектить его через даггер
// в в кастомном activityTestRule, которое в методе beforeActivityLaunched
// будет запихивать тестовый apoComponent в app)
//...

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class) {

        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            DaggerAppComponent.builder()
        }

    };


    @Before
    public void setUp() {
        //fragment = new CollectionsFragment();
//        DaggerFragmentComponent.builder().fragmentModule(new FragmentModule(Modes.COLLECTIONS))
//                .build().injectPresenter(fragment);

    }


    protected void beforeActivityLaunched() {
    }


    @Test
    public void testAllLabelsVisible() {

        onView(withId(R.id.view_pager)).perform(ViewActions.swipeLeft());
        ViewInteraction collectionTab = onView(withText(R.string.tab_collections));
        Assert.assertNotNull(collectionTab);
        collectionTab.perform(click());
        collectionTab.check(matches(ViewMatchers.isSelected()));

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

    }
}

