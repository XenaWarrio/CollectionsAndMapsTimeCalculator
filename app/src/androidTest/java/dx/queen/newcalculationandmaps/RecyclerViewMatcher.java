package dx.queen.newcalculationandmaps;

import android.content.res.Resources;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewMatcher {
    private final RecyclerView recyclerView;


    public RecyclerViewMatcher(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }


    public Matcher<View> atPosition(final int position) {
        return atPositionOnView(position, -1);
    }

    public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

        return new TypeSafeMatcher<View>() {
            Resources resources = null;
            View childView;

            public void describeTo(Description description) {
//                String idDescription = Integer.toString(recyclerViewId);
//                if (this.resources != null) {
//                    try {
//                        idDescription = this.resources.getResourceName(recyclerViewId);
//                    } catch (Resources.NotFoundException var4) {
//                        idDescription = String.format("%s (resource name not found)",
//                                new Object[] { recyclerView });
//                    }
//                }
//
//                description.appendText("with id: " + idDescription);
                description.appendText("FUCK_UP!"); // TODO: fix later
            }

            public boolean matchesSafely(View view) {

                this.resources = view.getResources();

                if (childView == null) {
                    if (recyclerView != null) {
                        childView = recyclerView.findViewHolderForAdapterPosition(position).itemView;
                    }
                    else {
                        return false;
                    }
                }

                return view == (targetViewId == -1 ? childView : childView.findViewById(targetViewId));
            }
        };
    }
}
