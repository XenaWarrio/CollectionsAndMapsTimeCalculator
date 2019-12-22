package dx.queen.newcalculationandmaps;

import android.view.View;
import android.widget.ProgressBar;

import org.hamcrest.Matcher;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class CertainViewAction {
    public static ViewAction checkTextView(final int id, String text) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Edit text with text" + text;
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                onView(withId(v.getId())).check(matches(hasDescendant(withText(text))));
            }
        };
    }


    public static ViewAction checkProgressBar(final int id, ViewMatchers.Visibility mode) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Check Progress Bar Visibility";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                onView(withId(v.getId())).check(selectedDescendantsMatch(isAssignableFrom(ProgressBar.class), withEffectiveVisibility(mode)));

            }
        };
    }

}

