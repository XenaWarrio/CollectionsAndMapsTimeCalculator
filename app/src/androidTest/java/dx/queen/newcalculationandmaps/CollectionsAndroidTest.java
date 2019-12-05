package dx.queen.newcalculationandmaps;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import dx.queen.newcalculationandmaps.dto.CalculationResult;
import dx.queen.newcalculationandmaps.dto.Modes;
import dx.queen.newcalculationandmaps.dto.task.TaskData;
import dx.queen.newcalculationandmaps.ui.MainActivity;
import dx.queen.newcalculationandmaps.ui.fragments.CollectionAdapter;
import dx.queen.newcalculationandmaps.ui.fragments.CollectionsFragment;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4ClassRunner.class)
public class CollectionsAndroidTest {

    CollectionAdapter adapter;
    CollectionsFragment fragment;


    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp(){
        fragment = new CollectionsFragment();
        activityTestRule.getActivity().getSupportFragmentManager().beginTransaction().add(fragment , Modes.COLLECTIONS).commit();
        adapter = new CollectionAdapter();
    }

    @Test
    public void testProgressBar(){
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));

        onView(withId(R.id.et_operations)).perform(ViewActions.typeText("0"));
        onView(withId(R.id.et_threads)).perform(ViewActions.typeText("6"));
        onView(withId(R.id.bt_start)).perform(ViewActions.click());
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));

        onView(withId(R.id.et_operations)).perform(ViewActions.typeText("6"));
        onView(withId(R.id.et_threads)).perform(ViewActions.typeText("6"));
        onView(withId(R.id.bt_start)).perform(ViewActions.click());
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));

        fragment.setItems(testCalculator());
        onView(withId(R.id.tv_name)).check(matches(withText(containsString("3.0"))));



    }

    public List<CalculationResult> testCalculator(){
        List<TaskData>td= new ArrayList<>();
        for(TaskData r : td){
            r.setTime(3.0);
            r.getResult();
        }
        CalculationResult cr = td.get(0).getResult();
        List<CalculationResult> calculationResults = new ArrayList<>();
         for (CalculationResult c : calculationResults){
             c = cr;
         }
         return calculationResults;
    }
}
