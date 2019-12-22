package dx.queen.newcalculationandmaps;

import android.content.res.Resources;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class RecyclerViewMatcher {
    private final RecyclerView recyclerView;

    public RecyclerViewMatcher(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public Matcher<View> atPosition(final int position) {
        return atPositionOnView(position, -1);
    }

    private Matcher<View> atPositionOnView(final int position, final int targetViewId) {
        return new TypeSafeMatcher<View>() {
            Resources resources = null;
            View childView;

            public void describeTo(Description description) {
                String idDescription = recyclerView.toString();
                if (this.resources != null) {
                    try {
                        idDescription = this.resources.getResourceName(recyclerView.getId());
                    } catch (Resources.NotFoundException var4) {
                        idDescription = String.format("%s (resource name not found)", recyclerView);
                    }
                }

                description.appendText("RecyclerView with id: " + idDescription + " at position: " + position);
            }

            public boolean matchesSafely(View view) {
                this.resources = view.getResources();
                if (childView == null) {
                    if (recyclerView != null) {
                        childView = recyclerView.findViewHolderForAdapterPosition(position).itemView;
                    } else {
                        return false;
                    }
                }

                if (targetViewId == -1) {
                    return view == childView;
                } else {
                    final View targetView = childView.findViewById(targetViewId);
                    return view == targetView;
                }
            }
        };
    }
}
