package dx.queen.newcalculationandmaps.ui;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import dx.queen.newcalculationandmaps.R;
import dx.queen.newcalculationandmaps.dto.Modes;
import dx.queen.newcalculationandmaps.ui.fragments.CollectionsFragment;

public class FragmentsAdapter extends FragmentStatePagerAdapter {

    private final Fragment[] fragments = new Fragment[2];
    private final Context context;

    FragmentsAdapter(FragmentManager fm, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        final List<Fragment> fragmentsT = fm.getFragments();
        if (fragmentsT.isEmpty()) {
            this.fragments[0] = CollectionsFragment.newInstance(Modes.COLLECTIONS);
            this.fragments[1] = CollectionsFragment.newInstance(Modes.MAPS);
        } else {
            this.fragments[0] = fragmentsT.get(0);
            this.fragments[1] = fragmentsT.get(1);
        }
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.tab_collections);

            case 1:
                return context.getString(R.string.tab_maps);

            default:
                return "";
        }
    }
}
